package com.sda.todo_list;

import com.sda.todo_list.ui.model.MenuEnum;
import com.sda.todo_list.ui.window.ConfirmBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class ToDoListApplication extends Application {

    private static Stage window;
    private static BorderPane stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("ToDo List application");
        window.setOnCloseRequest(event -> {
            event.consume();
            closeProgram();
        });

        URL path = Paths.get("src/main/java/com/sda/todo_list/ui/layout/main.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(path);

        stage = (BorderPane) root;

        Scene scene = new Scene(stage, 1000, 600);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setWindow(MenuEnum menu) throws IOException {

        switch (menu) {

            case SHOW_TASKS: {
                URL path = Paths.get("src/main/java/com/sda/todo_list/ui/layout/showTasks.fxml")
                        .toUri().toURL();
                Parent root = FXMLLoader.load(path);
                stage.setCenter(root);
                break;
            }
            case CATEGORIES: {
                URL path = Paths.get("src/main/java/com/sda/todo_list/ui/layout/categories.fxml")
                        .toUri().toURL();
                Parent root = FXMLLoader.load(path);
                stage.setCenter(root);
                break;
            }
            case ADD_TASK: {
                URL path = Paths.get("src/main/java/com/sda/todo_list/ui/layout/addTask.fxml")
                        .toUri().toURL();
                Parent root = FXMLLoader.load(path);
                stage.setCenter(root);
                break;
            }
            case EXIT_PROGRAM: {
                closeProgram();
                break;
            }
            default: {
                stage.setCenter(null);
            }
        }
    }

    private static void closeProgram() {
        boolean exit = ConfirmBox.display("Confirm exit",
                "Are you sure want to exit?");
        if (exit) {
            window.close();
        }
    }
}
