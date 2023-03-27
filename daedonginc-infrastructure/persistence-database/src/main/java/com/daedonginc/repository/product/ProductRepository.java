package com.daedonginc.repository.product;

import java.util.Optional;

import com.daedonginc.entity.product.ProductEntity;

/**
 * @author domo
 * Created on 2023/03/25
 */
public interface ProductRepository {
	Optional<ProductEntity> findById(Long productId);

	ProductEntity save(ProductEntity productEntity);

	void deleteById(Long productId);
}
