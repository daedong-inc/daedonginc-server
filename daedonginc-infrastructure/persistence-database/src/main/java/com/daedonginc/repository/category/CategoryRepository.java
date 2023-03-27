package com.daedonginc.repository.category;

import java.util.Optional;

import com.daedonginc.entity.category.CategoryEntity;

/**
 * @author domo
 * Created on 2023/03/27
 */
public interface CategoryRepository {
	Optional<CategoryEntity> findById(Long categoryId);

	CategoryEntity save(CategoryEntity categoryEntity);

	void deleteById(Long categoryId);
}
