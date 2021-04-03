package com.project.tasks.controller;

import com.project.tasks.model.Task;
import com.project.tasks.requests.TaskPostRequestBody;
import com.project.tasks.requests.TaskPutRequestBody;
import com.project.tasks.service.TaskService;
import com.project.tasks.util.TaskCreator;
import com.project.tasks.util.TaskPostRequestBodyCreator;
import com.project.tasks.util.TaskPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Task> taskPage = new PageImpl<>(List.of(TaskCreator.createValidTask()));

        BDDMockito.when(taskServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(taskPage);

        BDDMockito.when(taskServiceMock.listAllNonPageable())
                .thenReturn(List.of(TaskCreator.createValidTask()));

        BDDMockito.when(taskServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(TaskCreator.createValidTask());

        BDDMockito.when(taskServiceMock.findByDescription(ArgumentMatchers.anyString()))
                .thenReturn(List.of(TaskCreator.createValidTask()));

        BDDMockito.when(taskServiceMock.save(ArgumentMatchers.any(TaskPostRequestBody.class)))
                .thenReturn(TaskCreator.createValidTask());

        BDDMockito.doNothing().when(taskServiceMock).replace(ArgumentMatchers.any(TaskPutRequestBody.class));

        BDDMockito.doNothing().when(taskServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("list returns list of tasks inside page object when successful")
    void list_ReturnsListOfTasksInsidePageObject_WhenSuccessful() {

        String expectedDescription = TaskCreator.createValidTask().getDescription();

        Page<Task> taskPage = taskController.list(null).getBody();

        Assertions.assertThat(taskPage).isNotNull();

        Assertions.assertThat(taskPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(taskPage.toList().get(0).getDescription()).isEqualTo(expectedDescription);
    }

    @Test
    @DisplayName("listAll returns list of tasks when successful")
    void listAll_ReturnsListOfTasks_WhenSuccessful() {

        String expectedDescription = TaskCreator.createValidTask().getDescription();

        List<Task> tasks = taskController.listAll().getBody();

        Assertions.assertThat(tasks)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(tasks.get(0).getDescription()).isEqualTo(expectedDescription);
    }

    @Test
    @DisplayName("findById returns task when successful")
    void findById_ReturnsTask_WhenSuccessful() {

        Long expectedId = TaskCreator.createValidTask().getId();

        Task task = taskController.findById(1).getBody();

        Assertions.assertThat(task).isNotNull();

        Assertions.assertThat(task.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByDescription returns list of tasks when successful")
    void findByDescription_ReturnsListOfTasks_WhenSuccessful() {

        String expectedDescription = TaskCreator.createValidTask().getDescription();

        List<Task> tasks = taskController.findByDescription("Task").getBody();

        Assertions.assertThat(tasks)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(tasks.get(0).getDescription()).isEqualTo(expectedDescription);
    }

    @Test
    @DisplayName("findByDescription returns an empty list of tasks when anime is not found")
    void findByDescription_ReturnsEmptyListOfTasks_WhenAnimeIsNotFound() {

        BDDMockito.when(taskServiceMock.findByDescription(ArgumentMatchers.anyString()))
                .thenReturn(List.of(TaskCreator.createValidTask()));

        List<Task> tasks = taskController.findByDescription("Task").getBody();

        Assertions.assertThat(tasks)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    @DisplayName("save returns task when successful")
    void save_ReturnsTask_WhenSuccessful() {

        Task task = taskController
                .save(TaskPostRequestBodyCreator.createTaskPostRequestBody())
                .getBody();

        Assertions.assertThat(task).isNotNull().isEqualTo(TaskCreator.createValidTask());
    }

    @Test
    @DisplayName("replace updates task when successful")
    void replace_UpdatesTask_WhenSuccessful() {

        Assertions.assertThatCode(() -> taskController.replace(TaskPutRequestBodyCreator.createTaskPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = taskController.replace(TaskPutRequestBodyCreator.createTaskPutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes task when successful")
    void delete_RemovesTask_WhenSuccessful() {

        Assertions.assertThatCode(() -> taskController.delete(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = taskController.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}