package github.boozoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * Created by zy on 11/12/16.
 */
public class AddBookController implements Initializable {

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void handleAddBookAction (final ActionEvent event)
    {
        System.out.print("Add book action.");
    }

    @FXML
    private void handleCancelAction (final ActionEvent event){

        Stage addBook = (Stage) cancelButton.getScene().getWindow();

        addBook.close();

    }

}
