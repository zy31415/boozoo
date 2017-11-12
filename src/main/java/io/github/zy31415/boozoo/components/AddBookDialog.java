package io.github.zy31415.boozoo.components;

import io.github.zy31415.boozoo.database.BoozooEMF;
import io.github.zy31415.boozoo.models.Author;
import io.github.zy31415.boozoo.models.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by zy on 7/2/17.
 */
public class AddBookDialog extends GridPane{

    private static Logger logger = LogManager.getLogger();

    private Stage primaryStage;

    public AddBookDialog(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("addbook.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            logger.error(exception.getStackTrace());
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
        EntityManager manager = BoozooEMF.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            createBookEntry(manager);
            transaction.commit();

        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Failed!", ex);
        } finally {
            manager.close();
        }
    }

    private void createBookEntry(EntityManager em) {

        Author author = createAuthor(em);

        Book book = new Book();
        TextField title = (TextField) getScene().lookup("#title");
        book.setTitle(title.getText());

        if (null != author) {
            book.addAuthor(author);
        }

        em.persist(book);
    }

    private Author createAuthor(EntityManager em) {
        Author author = null;

        TextField authorTextField = (TextField) getScene().lookup("#author");
        assert authorTextField != null;

        if (null == authorTextField.getText() || authorTextField.getText().trim().isEmpty()) {
            logger.debug("Author text field is null or empty string.");
            return author;
        }

        String authorName = authorTextField.getText().trim();

        Query query = em.createQuery("from Author where name = :authorName");
        query.setParameter("authorName", authorName);
        List<Author> results = query.getResultList();

        if (results.isEmpty()) {
            author = new Author();
            author.setName(authorName);
            em.persist(author);
            return author;
        }

        assert results.size() == 1;
        return results.get(0);
    }

    @FXML
    private void handleChooseFileButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
        );

        File file = fileChooser.showOpenDialog(primaryStage);

        TextField docPath = (TextField) lookup("#docPath");
        docPath.setText(file.toString());

    }

}
