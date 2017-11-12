package io.github.zy31415.boozoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("BooZoo");
        primaryStage.getIcons().add(new Image("zoo-book1.jpg"));

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("boozoo.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 700, 700));
        root.requestFocus();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
