package com.example.backend.repository;

import com.example.backend.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class TaskDTO {
    private final List<Task> TASKS = new ArrayList<>();

    public List<Task> getTasks() {
        return TASKS;
    }

    public Task create(Task task) {
        TASKS.add(task);
        return task;
    }

    public Task findById(Long id) {
        return TASKS.stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Task update(Task task) {
        var taskIndex = IntStream.range(0, TASKS.size())
                .filter(index -> TASKS.get(index).getId().equals(task.getId()))
                .findFirst()
                .orElse(-1);
        if (taskIndex > -1) {
            TASKS.set(taskIndex, task);
            return task;
        }
        return null;
    }

    public void delete(Long id) {
        var task = findById(id);
        if (task != null) {
            TASKS.remove(task);
        }
    }

}
