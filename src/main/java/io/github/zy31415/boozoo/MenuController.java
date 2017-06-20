package io.github.zy31415.boozoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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


    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
        menuBar.setFocusTraversable(true);

    }

    @FXML
    private void handleAddBookAction (final ActionEvent event) throws Exception
    {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(
                getClass().getClassLoader().getResource("addbook.fxml")
        );

        stage.setTitle("Add a book");
        stage.setScene(new Scene(root, 350, 400));
        stage.show();

    }


}