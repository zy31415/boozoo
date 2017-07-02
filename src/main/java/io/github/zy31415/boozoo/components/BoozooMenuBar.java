package io.github.zy31415.boozoo.components;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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

        Stage dialogStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("addbook.fxml"));

        Parent root = loader.load();

        dialogStage.setTitle("Add a book");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        dialogStage.setScene(new Scene(root, 350, 400));

        AddBookController addBookController = loader.getController();
        addBookController.setBookView((BookView)primaryStage.getScene().lookup("#bookView"));

        dialogStage.show();

    }

}
