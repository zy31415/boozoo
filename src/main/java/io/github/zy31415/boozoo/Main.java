package io.github.zy31415.boozoo;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(
                getClass().getClassLoader().getResource("boozoo.fxml")
        );
        primaryStage.setTitle("BooZoo");
        primaryStage.setScene(new Scene(root, 700, 700));


        Book book = new Book();
        book.setTitle("haha");

        ObservableList<Book> data = FXCollections.observableArrayList(
                book
        );

        TableView table = (TableView) root.lookup("#tableView");

        table.setItems(data);


        primaryStage.show();
    }


    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("JavaHelps");


    public static void main(String[] args) {

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        Query query = manager.createQuery("select title from Book");

        List results = query.getResultList();

        for (Object title : results) {
            System.out.print(title + "\n");
        }

        ENTITY_MANAGER_FACTORY.close();

        launch(args);


    }


    public static void create(int id, String name, int age) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new Student object
            Student stu = new Student();
            stu.setId(id);
            stu.setName(name);
            stu.setAge(age);

            // Save the student object
            manager.persist(stu);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

}
