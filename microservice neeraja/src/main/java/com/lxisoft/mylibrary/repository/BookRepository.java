package com.lxisoft.mylibrary.repository;

import com.lxisoft.mylibrary.domain.Author;
import com.lxisoft.mylibrary.domain.Book;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAuthorId(Long id);
	
	List<Book> findByCategoriesId(Long id);
}
