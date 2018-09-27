package com.lxisoft.mylibrary.repository;

import com.lxisoft.mylibrary.domain.Book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	/*@param id
	 *@return
	 */
	
	public List<Book> findByAuthorId(Long id);
	
	/*@param id
	 *@return
	 */
	
	public List<Book> findByCategorysId(Long id);

}
