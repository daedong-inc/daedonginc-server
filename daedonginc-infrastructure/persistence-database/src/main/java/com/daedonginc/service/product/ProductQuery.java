package com.daedonginc.service.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.entity.product.ProductEntity;
import com.daedonginc.model.category.CategoryLevel;
import com.daedonginc.repository.category.CategoryRepository;
import com.daedonginc.repository.product.ProductRepository;
import com.daedonginc.service.category.exception.NotFoundCategoryException;
import com.daedonginc.service.category.exception.NotFoundParentCategoryException;
import com.daedonginc.service.product.exception.NotFoundProductException;

/**
 * @author domo
 * Created on 2023/03/25
 */
@Service
@Transactional(readOnly = true)
public class ProductQuery {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public ProductQuery(
			ProductRepository productRepository,
			CategoryRepository categoryRepository
	) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public ProductEntity findById(final Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundProductException(productId));
	}

	public Page<ProductEntity> findAll(final Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Page<ProductEntity> findAllByParentId(final Pageable pageable, Long parentId) {
		CategoryEntity category = categoryRepository.findById(parentId)
				.orElseThrow(() -> new NotFoundCategoryException(parentId));

		if (category.getCategoryLevel() != CategoryLevel.LARGE) {
			throw new NotFoundParentCategoryException(parentId);
		}

		return productRepository.findAllByCategoryIn(pageable, category.getChildren());
	}

	public Page<ProductEntity> findAllByChildId(final Pageable pageable, Long childId) {
		CategoryEntity category = categoryRepository.findById(childId)
				.orElseThrow(() -> new NotFoundCategoryException(childId));

		return productRepository.findAllByCategory(pageable, category);
	}

	public Long count() {
		return productRepository.count();
	}

	public Long countByParentId(Long parentId) {
		CategoryEntity category = categoryRepository.findById(parentId)
				.orElseThrow(() -> new NotFoundCategoryException(parentId));

		if (category.getCategoryLevel() != CategoryLevel.LARGE) {
			throw new NotFoundParentCategoryException(parentId);
		}

		return productRepository.countByCategoryIn(category.getChildren());
	}

	public Long countByChildId(Long childId) {
		CategoryEntity category = categoryRepository.findById(childId)
				.orElseThrow(() -> new NotFoundCategoryException(childId));

		return productRepository.countByCategory(category);
	}

	public Page<ProductEntity> search(
			final Pageable pageable,
			String keyword,
			Long parentId,
			Optional<Long> childId,
			boolean hidden
	) {
		categoryRepository.findById(parentId)
				.orElseThrow(() -> new NotFoundCategoryException(parentId));

		return productRepository.searchProducts(pageable, keyword, parentId, childId, hidden);
	}
}
