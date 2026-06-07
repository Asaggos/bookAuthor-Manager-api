package com.iekakmi.bookAuthorManager.business.Services;

import com.iekakmi.bookAuthorManager.business.DTOs.AuthorDTO;
import com.iekakmi.bookAuthorManager.business.DTOs.BookDTO;
import com.iekakmi.bookAuthorManager.domain.entities.Author;
import com.iekakmi.bookAuthorManager.domain.entities.Book;
import com.iekakmi.bookAuthorManager.domain.repositories.AuthorRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    //GET ALL AUTHORS
    public List<AuthorDTO> getAuthors(){
        return authorRepo.findAll().
                stream()
                .map(ath -> {
                    AuthorDTO dto = new AuthorDTO();
                    dto.setId(ath.getId());
                    dto.setName(ath.getName());
                    dto.setNationality(ath.getNationality());
                    dto.setBirthDate(ath.getBirthDate());

                    dto.setBookIsbn(
                            ath.getBooks()
                            .stream()
                            .map(Book::getIsbn)
                            .collect(Collectors.toList())
                    );

                    return dto;
                }).collect(Collectors.toList());
    }

    //FIND AUTHOR BY ID
    public AuthorDTO findAuthorById(int id){
        Author ath = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        AuthorDTO dto = new AuthorDTO();
        dto.setId(ath.getId());
        dto.setName(ath.getName());
        dto.setBookIsbn(
                ath.getBooks()
                .stream()
                .map(Book::getIsbn)
                .collect(Collectors.toList())
        );
        return dto;
    }

    //CREATE AUTHOR
    @Transactional
    public int createAuthor(@Valid AuthorDTO dto){
        Author ath = new Author();
        ath.setName(dto.getName());
        ath.setNationality(dto.getNationality());
        ath.setBirthDate(dto.getBirthDate());

        Author saved = authorRepo.save(ath);
        dto.setId(saved.getId());
        return saved.getId();
    }

    //UPDATE AUTHOR
    @Transactional
    public AuthorDTO updateAuthor(@Valid AuthorDTO dto){
        Author ath = authorRepo.findById(dto.getId())
                .orElseThrow(()-> new RuntimeException("Author not found"));

        ath.setName(dto.getName());
        ath.setNationality(dto.getNationality());
        ath.setBirthDate(dto.getBirthDate());

        authorRepo.save(ath);
        return dto;
    }

    //DELETE AUTHOR
    @Transactional
    public void deleteAuthor(int id){
        authorRepo.deleteById(id);
    }

    // /authors/{id}/books
    @Transactional
    public List<BookDTO> findBookByAuthorId(int id){
        Author ath = authorRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Author not found!"));

        return ath.getBooks()
                .stream()
                .map(book -> {
                    BookDTO dto = new BookDTO();
                    dto.setIsbn(book.getIsbn());
                    dto.setTitle(book.getTitle());
                    dto.setCategory(book.getCategory());
                    return dto;
                }).collect(Collectors.toList());
    }


}
