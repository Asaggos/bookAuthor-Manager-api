package com.iekakmi.bookAuthorManager.business.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BookDTO {

    private String isbn;

    @NotNull(message = "Title cannot be null!")
    @NotBlank(message = "Title cannot be blank!")
    private String title;

    private String category;

    private int publicationYear;

    private List<Integer> authorsId;


    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public int getPublicationYear() {return publicationYear;}
    public void setPublicationYear(int publicationYear) {this.publicationYear = publicationYear;}

    public List<Integer> getAuthorsId() {return authorsId;}
    public void setAuthorsId(List<Integer> authorsId) {this.authorsId = authorsId;}

}
