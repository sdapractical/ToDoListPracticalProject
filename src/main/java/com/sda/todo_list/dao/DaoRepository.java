package com.sda.todo_list.dao;

import com.sda.todo_list.dao.impl.CategoryDaoImpl;
import com.sda.todo_list.dao.impl.TaskDaoImpl;

public class DaoRepository {

    private static final CategoryDao categoryDao = new CategoryDaoImpl();
    private static final TaskDao taskDao = new TaskDaoImpl();

    public static CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public static TaskDao getTaskDao() {
        return taskDao;
    }
}
