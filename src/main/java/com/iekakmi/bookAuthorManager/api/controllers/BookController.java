package com.iekakmi.bookAuthorManager.api.controllers;

import com.iekakmi.bookAuthorManager.business.DTOs.BookDTO;
import com.iekakmi.bookAuthorManager.business.Services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {this.bookService = bookService;}

    //GET ALL BOOKS (METHOD: GET) /books
    @GetMapping
    public ResponseEntity<?> getBooks(){
        List<BookDTO> response = bookService.getBooks();
        return ResponseEntity.ok(response);
    }

    //FIND BOOK BY ISBN (METHOD: GET) /books/{isbn}
    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn){
        BookDTO response = bookService.findByIsbn(isbn);
        return ResponseEntity.ok(response);
    }

    //CREATE BOOK (METHOD: POST) /books
    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody BookDTO bookDTO){
        String isbn = bookService.createBook(bookDTO);
        return new ResponseEntity<>(isbn, HttpStatus.CREATED);
    }

    //UPDATE BOOK (METHOD: PUT) books/{isbn}
    @PutMapping
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookDTO bookDTO){
        BookDTO updated = bookService.updateBook(bookDTO);
        return ResponseEntity.ok(updated);
    }

    //DELETE BOOK (METHOD: DELETE) /books/{isbn}
    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBook(@Valid @PathVariable String isbn){
        bookService.deleteBook(isbn);
        return ResponseEntity.ok().build();
    }

    //INSERT A LIST OF AUTHORS IN A BOOK (METHOD: POST) /books/{isbn}/authors*    *A LIST OF AUTHORS IDS
    @PostMapping("/{isbn}/authors")
    public ResponseEntity<?> assignAuthorsToBook(@Valid @PathVariable String isbn,@RequestBody List<Integer> authorsIds){
        BookDTO response = bookService.assignAuthorToBook(isbn,authorsIds);
        return ResponseEntity.ok(response);
    }

}
