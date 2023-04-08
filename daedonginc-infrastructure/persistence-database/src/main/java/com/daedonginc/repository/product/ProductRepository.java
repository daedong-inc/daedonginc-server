package com.daedonginc.repository.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
}
