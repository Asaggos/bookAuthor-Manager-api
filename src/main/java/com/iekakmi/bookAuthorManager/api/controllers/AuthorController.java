package com.iekakmi.bookAuthorManager.api.controllers;

import com.iekakmi.bookAuthorManager.business.DTOs.AuthorDTO;
import com.iekakmi.bookAuthorManager.business.DTOs.BookDTO;
import com.iekakmi.bookAuthorManager.business.Services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {this.authorService = authorService;}


    //GET ALL AUTHORS (METHOD: GET) /authors
    @GetMapping
    public ResponseEntity<?> getAuthors(){
        List<AuthorDTO> response = authorService.getAuthors();
        return ResponseEntity.ok(response);
    }

    //FIND AUTHOR BY ID (METHOD: GET) /authors/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Integer id){

        AuthorDTO response = authorService.findAuthorById(id);
        return ResponseEntity.ok(response);
    }

    //CREATE AUTHOR (METHOD: POST) /authors
    @PostMapping
    public ResponseEntity<?> createAuthor(@Valid @RequestBody AuthorDTO authorDTO){

        int id = authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    //UPDATE AUTHOR (METHOD: PUT)
    @PutMapping
    public ResponseEntity<?> updateAuthor(@Valid @RequestBody AuthorDTO authorDTO){
        AuthorDTO updated = authorService.updateAuthor(authorDTO);
        return ResponseEntity.ok(updated);
    }

    //DELETE AUTHOR (METHOD: DELETE) /authors/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer id){
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }

    //FIND AUTHOR'S BOOKS BY HIS ID (METHOD: GET)/authors/{id}/books
    @GetMapping("/{id}/books")
    public ResponseEntity<?> getBooksByAuthorId(@PathVariable Integer id){
        List<BookDTO> response = authorService.findBookByAuthorId(id);
        return ResponseEntity.ok(response);
    }

}
