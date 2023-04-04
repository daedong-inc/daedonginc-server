package com.daedonginc.repository.category;

import java.util.List;
import java.util.Optional;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.model.category.CategoryLevel;

/**
 * @author domo
 * Created on 2023/03/27
 */
public interface CategoryRepository {
	Optional<CategoryEntity> findById(Long categoryId);

	CategoryEntity save(CategoryEntity categoryEntity);

	void deleteById(Long categoryId);

	List<CategoryEntity> findAllByCategoryLevel(CategoryLevel level);

	List<CategoryEntity> findAllByParentIsNull();

	List<CategoryEntity> findAllByParentId(Long parentCategoryId);
}
