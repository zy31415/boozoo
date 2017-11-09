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

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("boozoo.fxml"));

        Parent root = loader.load();

        primaryStage.setTitle("BooZoo");
        primaryStage.getIcons().add(new Image("zoo-book1.jpg"));
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
