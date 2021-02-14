package com.sda.todo_list.ui.controller;

import com.sda.todo_list.dao.CategoryDao;
import com.sda.todo_list.dao.DaoRepository;
import com.sda.todo_list.dao.TaskDao;
import com.sda.todo_list.domain.Category;
import com.sda.todo_list.domain.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {

    private final CategoryDao categoryDao = DaoRepository.getCategoryDao();
    private final TaskDao taskDao = DaoRepository.getTaskDao();

    @FXML
    private TextField taskName;
    @FXML
    private TextField taskDescription;
    @FXML
    private ChoiceBox<Category> categoriesBox;
    @FXML
    private DatePicker taskDate;
    @FXML
    private TextField taskTime;
    @FXML
    private Label messageLabel;
    @FXML
    private Button addTaskButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoriesBox.getItems().addAll(categoryDao.getCategories());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        addTaskButton.setOnAction(event -> {

            messageLabel.setText("");

            if (taskName.getText().isEmpty()) {
                messageLabel.setText("Please enter a task name.");
                return;
            }

            Task task = new Task();
            task.setName(taskName.getText());
            task.setDescription(taskDescription.getText());
            task.setCategory(categoriesBox.getValue());
            task.setDate(taskDate.getValue());

            if (!taskTime.getText().isEmpty()) {
                try {
                    task.setTime(LocalTime.parse(taskTime.getText(), formatter));
                } catch (DateTimeParseException e) {
                    messageLabel.setText("Please use 'hh:mm' 24h format to store time.");
                    return;
                }
            }

            taskDao.saveOrUpdate(task);
            messageLabel.setText("Task saved successfully.");
            taskName.setText("");
            taskDescription.setText("");
            categoriesBox.setValue(null);
            taskDate.setValue(null);
            taskTime.setText("");
        });
    }
}
