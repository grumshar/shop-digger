package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findCategoriesByParentCategoryId(Long id);

}
