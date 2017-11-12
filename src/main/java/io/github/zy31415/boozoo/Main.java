package io.github.zy31415.boozoo;

import io.github.zy31415.boozoo.database.BoozooEMF;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.Server;

import java.sql.SQLException;


public class Main extends Application {

    private static Logger logger = LogManager.getLogger();

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

    public static void main (String[] args) throws SQLException {
        Server server = Server.createTcpServer(
                "-baseDir", "/Users/yang.zhang/workspace/boozoo/temp/boozoo/db").start();
        logger.debug("Server status: {}", server.getStatus());
        BoozooEMF.setPort(server.getPort());

        launch(args);

        server.stop();
        logger.debug("Server status: {}", server.getStatus());
    }

}
