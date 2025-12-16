package com.gerenciamento.domain.applicationservice;

import com.gerenciamento.domain.entity.Task;
import com.gerenciamento.domain.exception.InvalidTaskStatusException;
import com.gerenciamento.domain.exception.TaskNotFoundException;
import com.gerenciamento.domain.model.TaskStatus;
import com.gerenciamento.domain.repository.TaskRepository;
import com.gerenciamento.infrastructure.config.AppConfigProperties;
import com.gerenciamento.infrastructure.dto.SaveTaskDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.gerenciamento.infrastructure.util.PaginationHelper.createPageable;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    private final ProjectService projectService;
    private final MemberService memberService;

    private final AppConfigProperties appConfigProperties;

    @Transactional
    public Task createTask(SaveTaskDataDTO saveTaskDataDTO){

        Task task = Task
                .builder       ()
                .title         (saveTaskDataDTO.getTitle())
                .description   (saveTaskDataDTO.getDescription())
                .numberOfDays  (saveTaskDataDTO.getNumberOfDays())
                .status        (TaskStatus.PENDING)
                .project       (Objects.isNull(saveTaskDataDTO.getProjectId()) ? null : projectService.loadProject(saveTaskDataDTO.getProjectId()))
                .assignedMember(Objects.isNull(saveTaskDataDTO.getMemberId())  ? null : memberService.loadMemberById(saveTaskDataDTO.getMemberId()))
                .build         ();

        taskRepository.save(task);

        return task;
    }

    public Task loadTask(String id) {
        return taskRepository
                .findById   (id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Transactional
    public void deleteTask(String id) {
        taskRepository.delete(loadTask(id));
    }

    @Transactional
    public Task updateTask(String id, SaveTaskDataDTO saveTaskDataDTO) {
        Task task = loadTask(id);

        task.setTitle         (saveTaskDataDTO.getTitle());
        task.setDescription   (saveTaskDataDTO.getDescription());
        task.setNumberOfDays  (saveTaskDataDTO.getNumberOfDays());
        task.setStatus        (convertToTaskStatus(saveTaskDataDTO.getStatus()));
        task.setProject       (Objects.isNull(saveTaskDataDTO.getProjectId()) ? null : projectService.loadProject(saveTaskDataDTO.getProjectId()));
        task.setAssignedMember(Objects.isNull(saveTaskDataDTO.getMemberId())  ? null : memberService.loadMemberById(saveTaskDataDTO.getMemberId()));

        return task;
    }

    public Page<Task> findTasks(
        String projectId,
        String memberId,
        String status,
        String partialTitle,
        Integer page,
        String directionStr,
        List<String> properties
    ) {
        return taskRepository.find(
                projectId,
                memberId,
                Optional.ofNullable(status).map(this::convertToTaskStatus).orElse(null),
                partialTitle,
                createPageable(page, appConfigProperties.getGeneral().getPageSize(), directionStr, properties)
        );
    }

    private TaskStatus convertToTaskStatus(String statusStr) {
        try {
            return TaskStatus.valueOf(statusStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidTaskStatusException(statusStr);
        }
    }
}
