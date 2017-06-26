package io.github.zy31415.boozoo;

import io.github.zy31415.boozoo.database.EmProvider;
import io.github.zy31415.boozoo.database.EmProvider2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ResourceBundle;

/**
 * Created by zy on 11/12/16.
 */
public class AddBookController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField title;

    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void handleAddBookAction (final ActionEvent event)
    {
        createBookEntry();

    }

    @FXML
    private void handleCancelAction (final ActionEvent event){

        Stage addBook = (Stage) cancelButton.getScene().getWindow();

        addBook.close();

    }

    private void createBookEntry(){
        // Create an EntityManager
//        EntityManager manager = EmProvider.getInstance().getEntityManagerFactory().createEntityManager();
        EntityManager manager = EmProvider2.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new Student object
            Book book = new Book();

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
