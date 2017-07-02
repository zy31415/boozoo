package io.github.zy31415.boozoo.database;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "firstNmae")
    private String firstNmae;

    @Column(name = "lastName")
    private String lastName;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstNmae;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString () {
        return lastName + ", " + firstNmae;
    }
}
