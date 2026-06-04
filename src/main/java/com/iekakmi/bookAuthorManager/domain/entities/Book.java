package com.iekakmi.bookAuthorManager.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @NotBlank(message = "Title required")
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "publicationYear")
    private int publicationYear;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public int getPublicationYear() {return publicationYear;}
    public void setPublicationYear(int publicationYear) {this.publicationYear = publicationYear;}

    public List<Author> getAuthors() {return authors;}
    public void setAuthors(List<Author> authors) {this.authors = authors;}
}
