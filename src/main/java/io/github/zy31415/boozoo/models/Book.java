package io.github.zy31415.boozoo.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @ManyToMany(cascade = { CascadeType.ALL }, fetch= FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private Set<Author> authors = new HashSet<>();

    public Set<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        assert null != author;
        authors.add(author);
    }


    @ManyToMany(cascade = { CascadeType.ALL }, fetch= FetchType.EAGER)
    @JoinTable(
            name = "book_tag",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> tags = new HashSet<>(0);

    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        assert null != tag;
        tags.add(tag);
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

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
