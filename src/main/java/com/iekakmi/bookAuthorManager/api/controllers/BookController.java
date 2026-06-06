package com.iekakmi.bookAuthorManager.api.controllers;

import com.iekakmi.bookAuthorManager.business.DTOs.BookDTO;
import com.iekakmi.bookAuthorManager.business.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    //GET ALL BOOKS (METHOD: GET) /books
    @GetMapping
    public List<BookDTO> getBooks(){
        return bookService.getBooks();
    }

    //FIND BOOK BY ISBN (METHOD: GET) /books/{isbn}
    @GetMapping("/{isbn}")
    public BookDTO getBookByIsbn(@PathVariable String isbn){
        return bookService.findByIsbn(isbn);
    }

    //CREATE BOOK (METHOD: POST) /books
    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO){
        return bookService.createBook(bookDTO);
    }

    //UPDATE BOOK (METHOD: PUT) books/{isbn}
    @PutMapping("/{isbn}")
    public BookDTO updateBook(@PathVariable String isbn,@RequestBody BookDTO bookDTO){
        return bookService.updateBook(isbn,bookDTO);
    }

    //DELETE BOOK (METHOD: DELETE) /books/{isbn}
    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn){
        bookService.deleteBook(isbn);
    }

    //INSERT A LIST OF AUTHORS IN A BOOK (METHOD: POST) /books/{isbn}/authors*    *A LIST OF AUTHORS IDS
    @PostMapping("/{isbn}/authors")
    public BookDTO assignAuthorsToBook(@PathVariable String isbn,@RequestBody List<Integer> authorsIds){
        return bookService.assignAuthorToBook(isbn,authorsIds);
    }

}
