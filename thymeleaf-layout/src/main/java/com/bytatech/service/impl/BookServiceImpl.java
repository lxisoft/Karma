package com.bytatech.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytatech.domain.Book;
import com.bytatech.repository.BookRepository;
import com.bytatech.service.AuthorService;
import com.bytatech.service.BookService;
import com.bytatech.service.PublisherService;
import com.bytatech.service.dto.BookDTO;
import com.bytatech.service.mapper.BookMapper;

/**
 * Service Implementation for managing Book.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

	private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	private final BookRepository bookRepository;

	private final BookMapper bookMapper;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private PublisherService publisherService;

	public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
	}

	/**
	 * Save a book.
	 *
	 * @param bookDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public BookDTO save(BookDTO bookDTO) {
		log.debug("Request to save Book : {}", bookDTO);
		Book book = bookMapper.toEntity(bookDTO);
		book = bookRepository.save(book);
		return bookMapper.toDto(book);
	}

	/**
	 * Get all the books.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BookDTO> findAll() {
		log.debug("Request to get all Books");
		LinkedList<BookDTO> books = bookRepository.findAll().stream()
				.map(bookMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));

		for (BookDTO bookDto : books) {

			bookDto.setAuthorName(authorService.findOne(bookDto.getAuthorId()).getName());
			bookDto.setPublisherName(publisherService.findOne(bookDto.getPublisherId()).getName());

		}

		return books;
	}

	/**
	 * Get one book by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public BookDTO findOne(Long id) {
		log.debug("Request to get Book : {}", id);
		Book book = bookRepository.findOne(id);
		BookDTO bookDto = bookMapper.toDto(book);

		bookDto.setAuthorName(authorService.findOne(bookDto.getAuthorId()).getName());
		bookDto.setPublisherName(publisherService.findOne(bookDto.getPublisherId()).getName());

		return bookDto;
	}

	/**
	 * Delete the book by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Book : {}", id);
		bookRepository.delete(id);
	}
}
