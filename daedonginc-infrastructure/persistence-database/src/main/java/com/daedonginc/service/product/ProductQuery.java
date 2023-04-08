package com.daedonginc.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.product.ProductEntity;
import com.daedonginc.repository.product.ProductRepository;
import com.daedonginc.service.product.exception.NotFoundProductException;

/**
 * @author domo
 * Created on 2023/03/25
 */
@Service
@Transactional(readOnly = true)
public class ProductQuery {
	private final ProductRepository productRepository;

	public ProductQuery(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductEntity findById(final Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundProductException(productId));
	}

	public Page<ProductEntity> findAll(final Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Long count() {
		return productRepository.count();
	}
}
