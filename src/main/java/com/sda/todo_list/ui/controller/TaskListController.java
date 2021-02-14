package com.sda.todo_list.ui.controller;

import com.sda.todo_list.dao.DaoRepository;
import com.sda.todo_list.dao.TaskDao;
import com.sda.todo_list.domain.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TaskListController implements Initializable {

    private final TaskDao taskDao = DaoRepository.getTaskDao();
    private List<Task> allTasks;
    private List<Task> undoneTasks;

    @FXML
    private TableView<Task> taskList;
    @FXML
    private Button deleteTask;
    @FXML
    private Button markAsDone;
    @FXML
    private CheckBox showUndone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        allTasks = taskDao.getTasks();
        undoneTasks = getUndoneTasks();
        taskList.getItems().addAll(allTasks);

        deleteTask.setOnAction(event -> {

            Task task = taskList.getSelectionModel().getSelectedItem();
            taskDao.delete(task);
            taskList.getItems().remove(task);
            taskList.refresh();
        });

        markAsDone.setOnAction(event -> {

            Task task = taskList.getSelectionModel().getSelectedItem();
            task.setCompleted(true);
            taskDao.saveOrUpdate(task);
            taskList.refresh();
        });

        showUndone.setOnAction(event -> {

            taskList.getItems().clear();

            // This solution uses database
//            if (showUndone.isSelected()) {
//                taskList.getItems().addAll(taskDao.getTasksByCompleted(false));
//            } else {
//                taskList.getItems().addAll(taskDao.getTasks());
//            }

            //This solution does not use database
            if (showUndone.isSelected()) {
                taskList.getItems().addAll(undoneTasks);
            } else {
                taskList.getItems().addAll(allTasks);
            }

            taskList.refresh();
        });
    }

    private List<Task> getUndoneTasks() {

        List<Task> result = new ArrayList<>();

        for (Task task : allTasks) {
            if (!task.isCompleted()) {
                result.add(task);
            }
        }

        return result;
    }
}
