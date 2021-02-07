package com.sda.todo_list.ui.controller;

import com.sda.todo_list.dao.DaoRepository;
import com.sda.todo_list.dao.TaskDao;
import com.sda.todo_list.domain.Task;

public class MainController {

    public static void main(String[] args) {
        TaskDao taskDao = DaoRepository.getTaskDao();
        taskDao.addTask(new Task());
    }
}
