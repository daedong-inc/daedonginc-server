package com.daedonginc.service.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.model.category.CategoryLevel;
import com.daedonginc.repository.category.CategoryRepository;
import com.daedonginc.service.category.exception.DetailLevelIsCanNotHaveSubLevelException;
import com.daedonginc.service.category.exception.NotFoundCategoryException;
import com.daedonginc.service.category.exception.NotFoundParentCategoryException;

/**
 * @author domo
 * Created on 2023/03/27
 */
@Service
@Transactional
public class CategoryCommand {
	private final CategoryRepository categoryRepository;

	public CategoryCommand(final CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public CategoryEntity save(final Long parentCategoryId, final String name) {
		if (parentCategoryId == null) {
			CategoryEntity categoryEntity = CategoryEntity.newParentInstance(name);
			return categoryRepository.save(categoryEntity);
		}

		CategoryEntity parentCategoryEntity = categoryRepository.findById(parentCategoryId)
				.orElseThrow(() -> new NotFoundParentCategoryException(parentCategoryId));

		if (parentCategoryEntity.getCategoryLevel() == CategoryLevel.MIDDLE) {
			throw new DetailLevelIsCanNotHaveSubLevelException("중분류는 하위 카테고리를 가질 수 없습니다.");
		}
		CategoryEntity categoryEntity = CategoryEntity.newChildrenInstance(parentCategoryEntity, name,
				CategoryLevel.MIDDLE);
		return categoryRepository.save(categoryEntity);
	}

	public void deleteById(final Long categoryId) {
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundCategoryException(categoryId));
		categoryRepository.deleteById(categoryEntity.getId());
	}

	public void update(final Long categoryId, final Long parentId, final String name) {
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundCategoryException(categoryId));

		if (parentId == null) {
			categoryEntity.update(null, name, categoryEntity.getCategoryLevel());
			return;
		}

		CategoryEntity parentCategoryEntity = categoryRepository.findById(parentId)
				.orElseThrow(() -> new NotFoundParentCategoryException(parentId));

		categoryEntity.update(parentCategoryEntity, name, CategoryLevel.MIDDLE);
	}
}
