package io.github.zy31415.boozoo.models;

import io.github.zy31415.boozoo.models.Author;

import javax.persistence.*;
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

    @Column(name = "title")
    private String title;

    @Column(name = "path")
    private String path;

    @ManyToMany
    private Set<Author> authors = new HashSet<Author>(0);

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
