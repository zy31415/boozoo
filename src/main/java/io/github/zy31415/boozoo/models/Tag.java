package io.github.zy31415.boozoo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "name", unique = true)
    private String name;
}
