package com.bytatech.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bytatech.service.BookService;
import com.bytatech.service.dto.BookDTO;
import com.codahale.metrics.annotation.Timed;

@Controller
public class BookController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());


	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	/*    *//**
	 * POST /books : Create a new book.
	 *
	 * @param bookDTO
	 *            the bookDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with
	 *         body the new bookDTO, or with status 400 (Bad Request) if
	 *         the book has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	/*
	 * @PostMapping("/books")
	 *
	 * @Timed public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO
	 * bookDTO) throws URISyntaxException {
	 * log.debug("REST request to save Book : {}", bookDTO); if (bookDTO.getId()
	 * != null) { throw new
	 * BadRequestAlertException("A new book cannot already have an ID",
	 * ENTITY_NAME, "idexists"); } BookDTO result = bookService.save(bookDTO);
	 * return ResponseEntity.created(new URI("/api/books/" + result.getId()))
	 * .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
	 * result.getId().toString())) .body(result); }
	 *
	 *//**
	 * PUT /books : Updates an existing book.
	 *
	 * @param bookDTO
	 *            the bookDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         updated bookDTO, or with status 400 (Bad Request) if the
	 *         bookDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the bookDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 *//*
	 * @PutMapping("/books")
	 *
	 * @Timed public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO
	 * bookDTO) throws URISyntaxException {
	 * log.debug("REST request to update Book : {}", bookDTO); if
	 * (bookDTO.getId() == null) { return createBook(bookDTO); } BookDTO
	 * result = bookService.save(bookDTO); return ResponseEntity.ok()
	 * .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME,
	 * bookDTO.getId().toString())) .body(result); }
	 */

	/**
	 * GET /books : get all the books.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of books in
	 *         body
	 */
	@GetMapping("/")
	@Timed
	public String getAllBooks(Model model) {
		log.debug("REST request to get all Books");
		model.addAttribute("books", bookService.findAll());
		model.addAttribute("selectedBook", null);
		return "home";
	}

	/**
	 * GET /books/:id : get the "id" book.
	 *
	 * @param id
	 *            the id of the bookDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         bookDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/books/{id}")
	@Timed
	public String getBook(@PathVariable Long id, Model model) {
		log.debug("REST request to get Book : {}", id);
		BookDTO bookDTO = bookService.findOne(id);
		model.addAttribute("selectedBook", bookDTO);
		return "home :: selected-book";
	}

	/*    *//**
	 * DELETE /books/:id : delete the "id" book.
	 *
	 * @param id
	 *            the id of the bookDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 *//*
	 * @DeleteMapping("/books/{id}")
	 *
	 * @Timed public ResponseEntity<Void> deleteBook(@PathVariable
	 * Long id) { log.debug("REST request to delete Book : {}", id);
	 * bookService.delete(id); return
	 * ResponseEntity.ok().headers(HeaderUtil.
	 * createEntityDeletionAlert(ENTITY_NAME,
	 * id.toString())).build(); }
	 */
}
