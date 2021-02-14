package com.sda.todo_list.ui.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class ConfirmBox {

    private static boolean answer;

    public static boolean display(String title, String question) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Parent root = null;
        try {
            URL path = Paths.get("src/main/java/com/sda/todo_list/ui/layout/confirmBox.fxml").toUri().toURL();
            root = FXMLLoader.load(path);
        } catch (IOException  e) {
            e.printStackTrace();
            window.close();
        }

        Scene scene = new Scene(root);
        Label label = (Label) root.getChildrenUnmodifiable().get(0);
        label.setText(question);

        Button yes = (Button) root.getChildrenUnmodifiable().get(1);
        yes.setOnAction(event -> {
            answer = true;
            window.close();
        });

        Button no = (Button) root.getChildrenUnmodifiable().get(2);
        no.setOnAction(event -> {
            answer = false;
            window.close();
        });

        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
