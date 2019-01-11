package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.model.Category;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long>, PagingAndSortingRepository<Category, Long> {

    List<Category> findCategoriesByParentCategoryId(Long id);
    List<Category> findCategoriesByParentCategoryIdNotNull();
    List<Category> findCategoriesByParentCategoryIdNotNull(Pageable pageable);
    Category findCategoryById(Long id);

}
