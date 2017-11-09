package io.github.zy31415.boozoo.components;

import io.github.zy31415.boozoo.models.Book;
import io.github.zy31415.boozoo.database.EmProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;

/**
 * Created by zy on 7/2/17.
 */
public class AddBookDialog extends GridPane{

    private Stage primaryStage;

    public AddBookDialog(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("addbook.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        this.primaryStage = primaryStage;

    }

    @FXML
    private void handleAddBookAction (final ActionEvent event)
    {
        createBookEntry();

        BookView bookView = (BookView)(primaryStage.getScene().lookup("#bookView"));
        bookView.loadData();

        Stage dialogStage = (Stage)getScene().getWindow();

        dialogStage.close();

    }

    @FXML
    private void handleCancelAction (final ActionEvent event){
        Stage dialogStage = (Stage)getScene().getWindow();
        dialogStage.close();
    }

    private void createBookEntry(){
        // Create an EntityManager
        EntityManager manager = EmProvider.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new Student object
            Book book = new Book();

            TextField title = (TextField) getScene().lookup("#title");

            System.out.print("Book title: " + title.getText() + "\n");

            book.setTitle(title.getText());

            // Save the student object
            manager.persist(book);

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
