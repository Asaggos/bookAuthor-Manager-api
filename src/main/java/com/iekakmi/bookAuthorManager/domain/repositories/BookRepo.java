package com.iekakmi.bookAuthorManager.domain.repositories;

import com.iekakmi.bookAuthorManager.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,String> {
}
