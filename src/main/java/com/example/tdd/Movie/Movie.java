package com.example.tdd.Movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Movie {

    @Column
    private String name;

    @Id
    private Long id;
}
