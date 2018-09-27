package com.lxisoft.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Author.
 */
@Entity
@Table(name = "author")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @ManyToMany
    @JoinTable(name = "author_book",
               joinColumns = @JoinColumn(name = "authors_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "books_id", referencedColumnName = "id"))
    private Set<Book> books = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Author authorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Author phoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public Author books(Set<Book> books) {
        this.books = books;
        return this;
    }

    public Author addBook(Book book) {
        this.books.add(book);
        book.getAuthors().add(this);
        return this;
    }

    public Author removeBook(Book book) {
        this.books.remove(book);
        book.getAuthors().remove(this);
        return this;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Author author = (Author) o;
        if (author.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), author.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Author{" +
            "id=" + getId() +
            ", authorName='" + getAuthorName() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            "}";
    }
}
