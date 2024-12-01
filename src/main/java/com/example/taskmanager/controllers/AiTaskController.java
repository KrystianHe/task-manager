package com.example.taskmanager.controllers;

import com.example.taskmanager.enums.Completed;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.services.TaskService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai/tasks")
public class AiTaskController {

    private final ChatClient chatClient;
    private final TaskService taskService;

    public AiTaskController(ChatClient.Builder chatClient, TaskService taskService) {
        this.chatClient = chatClient.build();
        this.taskService = taskService;
    }

    @GetMapping("/generate-task")
    public Task generateTask(@RequestParam(value = "message", defaultValue = "Write something") String message) {
        try {
            var chatResponse = chatClient.prompt()
                    .user(message)
                    .call()
                    .chatResponse();

            String generatedTaskDescription = chatResponse.getResult().getOutput().getContent();

            var task = new Task();
            task.setDescription(generatedTaskDescription);
            task.setCompleted(Completed.NO);
            task.setId(null);

            return task;
        } catch (Exception e) {
            return new Task("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/confirm-task")
    public String confirmTask(@RequestParam(value = "message", defaultValue = "no") String message,
                              @RequestBody Task task) {
        if ("yes".equalsIgnoreCase(message)) {
            taskService.createTask(task);
            return "Task successfully added!";
        }
        return "Task was not added.";
    }

    @GetMapping("/estimate-time")
    public String estimateTaskTime(@RequestParam(value = "taskId") Long taskId) {
        var task = taskService.getTaskById(taskId);
        if (task == null) {
            return "Task not found!";
        }

        var taskDescription = task.getDescription();

        var estimatedTime = chatClient.prompt()
                .user("Estimate the time needed to complete this task: " + taskDescription)
                .call()
                .chatResponse()
                .getResult().getOutput().getContent();

        task.setEstimated(estimatedTime);
        taskService.updateTask(task.getId(), task);

        return estimatedTime;
    }

    @GetMapping("/alternative-solutions")
    public String generateAlternativeSolutions(@RequestParam(value = "taskId") Long taskId) {
        var task = taskService.getTaskById(taskId);
        if (task == null) {
            return "Task not found!";
        }

        var taskDescription = task.getDescription();

        return chatClient.prompt()
                .user("Provide alternative solutions for the following task: " + taskDescription)
                .call()
                .chatResponse()
                .getResult().getOutput().getContent();
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
