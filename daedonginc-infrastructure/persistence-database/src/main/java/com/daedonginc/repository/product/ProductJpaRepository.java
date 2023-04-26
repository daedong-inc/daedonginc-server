package com.daedonginc.repository.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.entity.product.ProductEntity;

/**
 * @author domo
 * Created on 2023/03/25
 */
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long>, ProductRepository {
	long count();

	Page<ProductEntity> findAllByCategoryIn(Pageable pageable, List<CategoryEntity> categories);

	long countByCategoryIn(List<CategoryEntity> categories);

	long countByCategory(CategoryEntity category);
}
