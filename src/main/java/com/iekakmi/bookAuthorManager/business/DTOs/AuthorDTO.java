package com.iekakmi.bookAuthorManager.business.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class AuthorDTO {

    private int id;

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be empty!")
    private String name;

    private String nationality;

    private LocalDate birthDate;

    private List<BookDTO> books;



    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getNationality() {return nationality;}
    public void setNationality(String nationality) {this.nationality = nationality;}

    public LocalDate getBirthDate() {return birthDate;}
    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}

    public List<BookDTO> getBooks() {return books;}
    public void setBooks(List<BookDTO> books) {this.books = books;}
}
