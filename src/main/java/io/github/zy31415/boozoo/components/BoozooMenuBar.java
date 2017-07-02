package io.github.zy31415.boozoo.components;


import io.github.zy31415.boozoo.database.Book;
import io.github.zy31415.boozoo.database.EmProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by zy on 7/2/17.
 */
public class BoozooMenuBar extends MenuBar {
    private static final String FXML = "menubar.fxml";

    public BoozooMenuBar() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Handle action related to "About" menu item.
     *
     * @param event Event on "About" menu item.
     */
    @FXML
    private void handleAboutAction(final ActionEvent event)
    {
        provideAboutFunctionality();
    }

    /**
     * Perform functionality associated with "About" menu selection or CTRL-A.
     */
    private void provideAboutFunctionality()
    {
        Alert about = new Alert(Alert.AlertType.INFORMATION);

        about.setTitle("About");
        about.setHeaderText("This a book management software.");

        WebView content = new WebView();
        content.getEngine().loadContent(
                "<ul> <li>Auther: Yang Zhang </li> </ul>"
        );
        content.setPrefSize(150, 60);

        about.getDialogPane().setContent(content);

        about.showAndWait();
    }

    /**
     * Handle action related to input (in this case specifically only responds to
     * keyboard event CTRL-A).
     *
     * @param event Input event.
     */
    @FXML
    private void handleKeyInput(final InputEvent event)
    {
        if (event instanceof KeyEvent)
        {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A)
            {
                provideAboutFunctionality();
            }
        }
    }

    @FXML
    private void addBookDialog(final ActionEvent event) throws Exception
    {
        Stage primaryStage = (Stage)getScene().getWindow();

        AddBookDialog root = new AddBookDialog(primaryStage);

        Stage dialogStage = new Stage();

        dialogStage.setTitle("Add a book");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        dialogStage.setScene(new Scene(root, 350, 400));

        dialogStage.show();

    }

    @FXML
    private void deleteBookDialog(final ActionEvent event) throws Exception
    {
        Stage primaryStage = (Stage)getScene().getWindow();
        BookView bookView = (BookView)(primaryStage.getScene().lookup("#bookView"));

        Book book = (Book)bookView.getSelectionModel().getSelectedItem();
        System.out.println(book);

        if (book == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please select a book");
            alert.setContentText("Please select a book");

            alert.showAndWait();

            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Deletion");
        alert.setHeaderText("Confirm deletion");
        alert.setContentText("Are you sure to delete book ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            EntityManager manager = EmProvider.getEntityManagerFactory().createEntityManager();
            EntityTransaction transaction = null;

            try {
                // Get a transaction
                transaction = manager.getTransaction();
                // Begin the transaction
                transaction.begin();

                // Save the student object
                manager.remove(book);

                Query query = manager.createQuery(
                        "delete from Book as b where b.title = :TITLE"
                );

                query.setParameter("TITLE", book.getTitle());

                int deleteCount = query.executeUpdate();

                System.out.println(deleteCount);

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

            bookView.loadData();


        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

}
