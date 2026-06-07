package com.iekakmi.bookAuthorManager.business.Services;

import com.iekakmi.bookAuthorManager.business.DTOs.BookDTO;
import com.iekakmi.bookAuthorManager.domain.entities.Author;
import com.iekakmi.bookAuthorManager.domain.entities.Book;
import com.iekakmi.bookAuthorManager.domain.repositories.AuthorRepo;
import com.iekakmi.bookAuthorManager.domain.repositories.BookRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;


    //GET ALL BOOKS
    public List<BookDTO> getBooks(){
        return bookRepo.findAll()
                .stream()
                .map(book -> {
                    BookDTO dto = new BookDTO();
                    dto.setIsbn(book.getIsbn());
                    dto.setTitle(book.getTitle());
                    dto.setCategory(book.getCategory());
                    dto.setPublicationYear(book.getPublicationYear());
                    dto.setAuthorsId(
                            book.getAuthors()
                            .stream()
                            .map(Author::getId)
                            .collect(Collectors.toList())
                    );
                    return dto;
                }).collect(Collectors.toList());
    }

    //FIND BOOK BY ISBN
    public BookDTO findByIsbn(String isbn) {
        Book book = bookRepo.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found!"));
        BookDTO dto = new BookDTO();
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setCategory(book.getCategory());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setAuthorsId(
                book.getAuthors()
                .stream()
                .map(Author::getId)
                .collect(Collectors.toList())
        );
        return dto;
    }

    //CREATE BOOK
    @Transactional
    public String createBook(@Valid BookDTO dto){
        Book book = new Book();
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setCategory(dto.getCategory());
        book.setPublicationYear(dto.getPublicationYear());

        if (dto.getAuthorsId() != null && !dto.getAuthorsId().isEmpty()) {
            List<Author> authors = authorRepo.findAllById(dto.getAuthorsId());
            book.setAuthors(authors);
        }

        bookRepo.save(book);
        return book.getIsbn();
    }

    //UPDATE BOOK
    @Transactional
    public BookDTO updateBook(@Valid BookDTO dto){
        Book book = bookRepo.findById(dto.getIsbn())
                .orElseThrow(()-> new RuntimeException("Book not found!"));

        book.setTitle(dto.getTitle());
        book.setCategory(dto.getCategory());
        book.setPublicationYear(dto.getPublicationYear());

        bookRepo.save(book);
        return dto;
    }

    //DELETE BOOK
    @Transactional
    public void deleteBook(String isbn){
        bookRepo.deleteById(isbn);
    }


    // /books/{id}/authors
    @Transactional
    public BookDTO assignAuthorToBook(String isbn,List<Integer> authorId){
        Book book = bookRepo.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found!"));

        List<Author> ath = authorRepo.findAllById(authorId);
        book.setAuthors(ath);

        bookRepo.save(book);

        BookDTO dto = new BookDTO();
        dto.setIsbn(book.getIsbn());
        dto.setAuthorsId(authorId);
        return dto;
    }


}
