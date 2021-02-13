package com.sda.todo_list.ui.controller;

import com.sda.todo_list.ToDoListApplication;
import com.sda.todo_list.ui.model.MenuEnum;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button showTasksButton;
    @FXML
    private Button categoriesButton;
    @FXML
    private Button addTaskButton;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showTasksButton.setOnAction(event -> {
            try {
                ToDoListApplication.setWindow(MenuEnum.SHOW_TASKS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        categoriesButton.setOnAction(event -> {
            try {
                ToDoListApplication.setWindow(MenuEnum.CATEGORIES);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addTaskButton.setOnAction(event -> {
            try {
                ToDoListApplication.setWindow(MenuEnum.ADD_TASK);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        exitButton.setOnAction(event -> {
            try {
                ToDoListApplication.setWindow(MenuEnum.EXIT_PROGRAM);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
