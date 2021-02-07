package com.sda.todo_list;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;

public class ToDoListApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage window = primaryStage;
        window.setTitle("ToDo List application");

        URL path = Paths.get("src/main/java/com/sda/todo_list/ui/layout/main.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(path);

        BorderPane stage = (BorderPane) root;

        Scene scene = new Scene(stage, 1000, 600);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
