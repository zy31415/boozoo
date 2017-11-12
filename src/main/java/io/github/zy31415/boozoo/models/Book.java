package io.github.zy31415.boozoo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table (name = "book")
public class Book implements Serializable {
    @Id
    @Column(name = "id", unique=true)
    @GeneratedValue
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "path")
    private String path;

    @ManyToMany
    private Set<Author> authors = new HashSet<>(0);

    public Set<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        assert null != author;
        authors.add(author);
    }


    @ManyToMany
    private Set<Tag> tags = new HashSet<>(0);

    public Book() {}

    public Book(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString () {
        return id + "\t" + title;
    }

}
