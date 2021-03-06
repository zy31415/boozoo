package io.github.zy31415.boozoo.components;

import io.github.zy31415.boozoo.database.BoozooEMF;
import io.github.zy31415.boozoo.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;

/**
 * Created by zy on 6/25/17.
 */
public class BookView extends TableView {

    private static final String BOOKVIEW_FXML = "bookview.fxml";

    public BookView () {
        loadFXML();
        loadData();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(BOOKVIEW_FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void loadData() {

        EntityManager manager = BoozooEMF.getEntityManagerFactory().createEntityManager();

        Query query = manager.createQuery(
                "select b from Book as b",
                Book.class
        );

        ObservableList<Book> books = FXCollections.observableArrayList(query.getResultList());

        setItems(books);

        manager.close();
    }

}
