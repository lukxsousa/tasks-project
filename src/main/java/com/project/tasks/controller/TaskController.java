package com.project.tasks.controller;

import com.project.tasks.model.Task;
import com.project.tasks.requests.TaskPostRequestBody;
import com.project.tasks.requests.TaskPutRequestBody;
import com.project.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("tasks")
@Log4j2
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<Page<Task>> list(Pageable pageable) {
        return ResponseEntity.ok(taskService.listAll(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Task>> listAll() {
        return ResponseEntity.ok(taskService.listAllNonPageable());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> findById(@PathVariable long id) {
        return ResponseEntity.ok(taskService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Task>> findByDescription(@RequestParam String description) {
        return ResponseEntity.ok(taskService.findByDescription(description));
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody @Valid TaskPostRequestBody taskPostRequestBody) {
        return new ResponseEntity<>(taskService.save(taskPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody TaskPutRequestBody taskPutRequestBody) {
        taskService.replace(taskPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
