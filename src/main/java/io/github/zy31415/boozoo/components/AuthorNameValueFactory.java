package io.github.zy31415.boozoo.components;

import io.github.zy31415.boozoo.models.Author;
import io.github.zy31415.boozoo.models.Book;
import javafx.beans.NamedArg;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class AuthorNameValueFactory extends PropertyValueFactory <List<Book>, String> {

    public AuthorNameValueFactory(@NamedArg("property") String property) {
        super(property);
    }

    /** {@inheritDoc} */
    @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<List<Book>, String> param) {
        Book book = (Book) param.getValue();

        Set<Author> authors = book.getAuthors();

        StringBuilder builder = new StringBuilder();

        Iterator<Author> itr = authors.iterator();

        while (itr.hasNext()) {
            Author author = itr.next();
            builder.append(author.getName());
            if (itr.hasNext()) {
                builder.append(", ");
            }
        }

        return new ReadOnlyStringWrapper(builder.toString().trim());
    }

}
