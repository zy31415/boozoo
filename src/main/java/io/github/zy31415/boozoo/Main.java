package io.github.zy31415.boozoo;

import java.util.List;

import io.github.zy31415.boozoo.database.EmProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(
                getClass().getClassLoader().getResource("boozoo.fxml")
        );

        primaryStage.setTitle("BooZoo");
        primaryStage.setScene(new Scene(root, 700, 700));

        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }

}
