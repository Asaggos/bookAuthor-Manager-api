package com.iekakmi.bookAuthorManager.api.controllers;

import com.iekakmi.bookAuthorManager.business.DTOs.AuthorDTO;
import com.iekakmi.bookAuthorManager.business.DTOs.BookDTO;
import com.iekakmi.bookAuthorManager.business.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    //GET ALL AUTHORS (METHOD: GET) /authors
    @GetMapping
    public List<AuthorDTO> getAuthors(){
        return authorService.getAuthors();
    }

    //FIND AUTHOR BY ID (METHOD: GET) /authors/{id}
    @GetMapping("/{id}")
    public AuthorDTO getById(@PathVariable int id){
        return authorService.findById(id);
    }

    //CREATE AUTHOR (METHOD: POST) /authors
    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO){
        return authorService.createAuthor(authorDTO);
    }

    //UPDATE AUTHOR (METHOD: PUT) /authors/{id}
    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable int id,@RequestBody AuthorDTO authorDTO){
        return authorService.updateAuthor(id,authorDTO);
    }

    //DELETE AUTHOR (METHOD: DELETE) /authors/{id}
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorService.deleteAuthor(id);
    }

    //FIND AUTHOR'S BOOKS BY HIS ID (METHOD: GET)/authors/{id}/books
    @GetMapping("/{id}/books")
    public List<BookDTO> getBooksByAuthorId(@PathVariable int id){
        return authorService.findBookByAuthorId(id);
    }

}
