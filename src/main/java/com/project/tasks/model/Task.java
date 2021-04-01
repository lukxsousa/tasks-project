package com.project.tasks.model;

import com.project.tasks.config.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The task description cannot be empty!")
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Override
    public String toString() {
        return "Task [id =" + id + ", description =" + description + ", status =" + status + "]";
    }
}
