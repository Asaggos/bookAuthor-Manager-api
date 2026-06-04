package com.iekakmi.bookAuthorManager.domain.repositories;

import com.iekakmi.bookAuthorManager.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author,Integer> {
}
