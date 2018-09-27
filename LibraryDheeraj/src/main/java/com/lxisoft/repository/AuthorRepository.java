package com.lxisoft.repository;

import com.lxisoft.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Author entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "select distinct author from Author author left join fetch author.books",
        countQuery = "select count(distinct author) from Author author")
    Page<Author> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct author from Author author left join fetch author.books")
    List<Author> findAllWithEagerRelationships();

    @Query("select author from Author author left join fetch author.books where author.id =:id")
    Optional<Author> findOneWithEagerRelationships(@Param("id") Long id);

}
