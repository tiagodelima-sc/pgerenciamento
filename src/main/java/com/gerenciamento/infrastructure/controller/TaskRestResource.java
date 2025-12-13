package com.gerenciamento.infrastructure.controller;

import com.gerenciamento.domain.applicationservice.TaskService;
import com.gerenciamento.domain.entity.Task;
import com.gerenciamento.infrastructure.dto.SaveTaskDataDTO;
import com.gerenciamento.infrastructure.dto.TaskDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.gerenciamento.infrastructure.controller.RestConstants.PATH_TASKS;

@RestController
@RequestMapping(PATH_TASKS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class TaskRestResource {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid SaveTaskDataDTO saveTaskDataDTO) {
        Task task = taskService.createTask(saveTaskDataDTO);

        return ResponseEntity
                .created(URI.create(PATH_TASKS + task.getId()))
                .body   (TaskDTO.create(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> loadTask(@PathVariable("id") String taskId) {
        Task task = taskService.loadTask(taskId);

        return ResponseEntity.ok(TaskDTO.create(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String id) {
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable("id") String taskId,
            @RequestBody @Valid SaveTaskDataDTO saveTaskDataDTO) {
        Task task = taskService.updateTask(taskId, saveTaskDataDTO);

        return ResponseEntity.ok(TaskDTO.create(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findTasks (
            @RequestParam(name = "projectId"   , required = false) String projectId,
            @RequestParam(name = "memberId"    , required = false) String memberId,
            @RequestParam(name = "status"      , required = false) String status,
            @RequestParam(name = "partialTitle", required = false) String partialTitle
    ) {
        List<Task> tasks = taskService.findTasks(projectId, memberId, status, partialTitle);

        return ResponseEntity.ok(tasks.stream().map(TaskDTO::create).toList());
    }

}
