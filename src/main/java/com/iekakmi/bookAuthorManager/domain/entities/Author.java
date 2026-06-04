package com.iekakmi.bookAuthorManager.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getNationality() {return nationality;}
    public void setNationality(String nationality) {this.nationality = nationality;}

    public LocalDate getBirthDate() {return birthDate;}
    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}

    public List<Book> getBooks() {return books;}
    public void setBooks(List<Book> books) {this.books = books;}
}
