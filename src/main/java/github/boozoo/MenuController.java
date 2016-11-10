package github.boozoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextInputDialog;
//import java.util.Optional;

import java.util.Optional;
import java.util.ResourceBundle;


public class MenuController implements Initializable
{
    @FXML
    private MenuBar menuBar;

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

    /**
     * Perform functionality associated with "About" menu selection or CTRL-A.
     */
    private void provideAboutFunctionality()
    {
        System.out.println("You clicked on About!");
    }


    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
        menuBar.setFocusTraversable(true);

    }

    @FXML
    private void handleAddBookAction(final ActionEvent event)
    {
        System.out.println("Add a book!");

        TextInputDialog dialog = new TextInputDialog("walter");

        dialog.setTitle("Add a book");
        dialog.setHeaderText("Add a book into database");
        dialog.setContentText("Book title:");


//// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
////        if (result.isPresent()){
////            System.out.println("Your name: " + result.get());
////        }
//
//// The Java 8 way to get the response value (with lambda expression).
//        result.ifPresent(name -> System.out.println("Your name: " + name));
//
    }


}