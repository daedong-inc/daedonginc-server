package com.daedonginc.service.category;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.repository.category.CategoryRepository;
import com.daedonginc.service.category.exception.NotFoundCategoryException;
import com.daedonginc.service.category.exception.NotFoundParentCategoryException;

/**
 * @author domo
 * Created on 2023/03/27
 */
@Service
@Transactional(readOnly = true)
public class CategoryQuery {
	private final CategoryRepository categoryRepository;

	public CategoryQuery(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public CategoryEntity findById(final Long categoryId) {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundCategoryException(categoryId));
	}

	public List<CategoryEntity> findAll() {
		return categoryRepository.findAllByParentIsNull();
	}

	public List<CategoryEntity> findAllByParentId(final Long parentId) {
		categoryRepository.findById(parentId)
				.orElseThrow(() -> new NotFoundParentCategoryException(parentId));
		return categoryRepository.findAllByParentId(parentId);
	}
}
