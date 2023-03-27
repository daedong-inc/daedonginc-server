package com.daedonginc.service.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.model.category.CategoryLevel;
import com.daedonginc.repository.category.CategoryRepository;
import com.daedonginc.service.category.exception.NotFoundCategoryException;

/**
 * @author domo
 * Created on 2023/03/27
 */
@Service
@Transactional
public class CategoryCommand {
	private final CategoryRepository categoryRepository;

	public CategoryCommand(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public CategoryEntity save(Long parentCategoryId, String name, CategoryLevel categoryLevel) {
		try {
			CategoryEntity parentCategoryEntity = categoryRepository.findById(parentCategoryId)
					.orElseThrow(() -> new NotFoundCategoryException(parentCategoryId));
			CategoryEntity categoryEntity = CategoryEntity.newChildrenInstance(parentCategoryEntity, name,
					categoryLevel);
			return categoryRepository.save(categoryEntity);
		} catch (NotFoundCategoryException e) {
			CategoryEntity categoryEntity = CategoryEntity.newParentInstance(name, categoryLevel);
			return categoryRepository.save(categoryEntity);
		}
	}

	public void deleteById(Long categoryId) {
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundCategoryException(categoryId));
		categoryRepository.deleteById(categoryEntity.getId());
	}
}
