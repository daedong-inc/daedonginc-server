package com.daedonginc.repository.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.entity.product.ProductEntity;

/**
 * @author domo
 * Created on 2023/03/25
 */
public interface ProductRepository {
	Optional<ProductEntity> findById(Long productId);

	ProductEntity save(ProductEntity productEntity);

	void deleteById(Long productId);

	long count();

	Page<ProductEntity> findAll(Pageable pageable);

	Page<ProductEntity> findAllByCategoryIn(Pageable pageable, List<CategoryEntity> categories);

	long countByCategoryIn(List<CategoryEntity> categories);

	Page<ProductEntity> findAllByCategory(Pageable pageable, CategoryEntity category);

	long countByCategory(CategoryEntity category);

	// Page<ProductEntity> findAllByParentId(Pageable pageable, Long parentId);

	// long countByParentId(Long parentId);
}
