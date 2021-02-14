package com.sda.todo_list.dao;

import com.sda.todo_list.domain.Task;

import java.util.List;

public interface TaskDao {

    void saveOrUpdate(Task task);
    List<Task> getTasks();
    List<Task> getTasksByCompleted(boolean completed);
    void delete(Task task);
}
