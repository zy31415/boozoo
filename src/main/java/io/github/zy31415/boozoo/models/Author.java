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

/**
 * Created by zy on 6/25/17.
 */

@Entity
@Table(name = "author")
public class Author implements Serializable {
    @Id
    @Column(name = "id", unique=true)
    @GeneratedValue
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
