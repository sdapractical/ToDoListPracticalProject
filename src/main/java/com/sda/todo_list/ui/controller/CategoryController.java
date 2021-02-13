package com.sda.todo_list.ui.controller;

import com.sda.todo_list.dao.CategoryDao;
import com.sda.todo_list.dao.DaoRepository;
import com.sda.todo_list.domain.Category;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.hibernate.exception.ConstraintViolationException;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    private final CategoryDao categoryDao = DaoRepository.getCategoryDao();

    @FXML
    private Button addCategoryButton;
    @FXML
    private Button deleteCategoryButton;
    @FXML
    private Button editCategoryButton;
    @FXML
    private TextField categoryNameTextFiled;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<Category> existingCategories;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        existingCategories.getItems().addAll(categoryDao.getCategories());

        addCategoryButton.setOnAction(event -> {

            messageLabel.setText("");

            String categoryName = categoryNameTextFiled.getText();

            if (null == categoryName || categoryName.isEmpty()) {
                messageLabel.setText("Please enter the category name.");
                return;
            }

            categoryName = categoryName.substring(0, 1).toUpperCase(Locale.ROOT) +
                    categoryName.substring(1).toLowerCase();

            Category category = new Category();
            category.setName(categoryName);

            try {
                categoryDao.saveOrUpdate(category);
            } catch (ConstraintViolationException e) {
                messageLabel.setText("Category " + categoryName + " already exists.");
                categoryNameTextFiled.clear();
                return;
            }
            messageLabel.setText("Category " + categoryName + " added.");
            existingCategories.getItems().add(category);
            categoryNameTextFiled.clear();
            existingCategories.refresh();
        });

        editCategoryButton.setOnAction(event -> {

            messageLabel.setText("");

            String categoryName = categoryNameTextFiled.getText();

            if (null == categoryName || categoryName.isEmpty()) {
                messageLabel.setText("Please enter the category name.");
                return;
            }

            categoryName = categoryName.substring(0, 1).toUpperCase(Locale.ROOT) +
                    categoryName.substring(1).toLowerCase();

            Category category = existingCategories.getSelectionModel().getSelectedItem();
            category.setName(categoryName);

            try {
                categoryDao.saveOrUpdate(category);
            } catch (ConstraintViolationException e) {
                messageLabel.setText("Category " + categoryName + " already exists.");
                categoryNameTextFiled.clear();
                return;
            }

            categoryNameTextFiled.clear();
            existingCategories.refresh();
        });

        deleteCategoryButton.setOnAction(event -> {

            messageLabel.setText("");

            Category category = existingCategories.getSelectionModel().getSelectedItem();
            if (null == category) {
                messageLabel.setText("Please choose a category to delete.");
                return;
            }

            //categoryDao.deleteCategoryByName(category.getName());
            categoryDao.deleteCategory(category);

            messageLabel.setText("Category " + category.getName() + " deleted");
            existingCategories.getItems().remove(category);
            existingCategories.refresh();
        });
    }
}
