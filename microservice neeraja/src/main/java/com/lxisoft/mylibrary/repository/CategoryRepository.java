package com.lxisoft.mylibrary.repository;

import com.lxisoft.mylibrary.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Category entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select distinct category from Category category left join fetch category.books",
        countQuery = "select count(distinct category) from Category category")
    Page<Category> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct category from Category category left join fetch category.books")
    List<Category> findAllWithEagerRelationships();

    @Query("select category from Category category left join fetch category.books where category.id =:id")
    Optional<Category> findOneWithEagerRelationships(@Param("id") Long id);

}
