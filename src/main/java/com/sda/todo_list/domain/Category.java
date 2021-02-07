package com.sda.todo_list.domain;

import java.util.List;

public class Category {

    private long id;
    private String name;
    private List<Task> taskList;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
