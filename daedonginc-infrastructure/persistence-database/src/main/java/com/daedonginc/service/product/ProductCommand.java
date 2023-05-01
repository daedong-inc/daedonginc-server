package com.daedonginc.service.product;

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
@Transactional
public class ProductCommand {
	private final ProductRepository productRepository;

	public ProductCommand(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductEntity save(ProductEntity productEntity) {
		return productRepository.save(productEntity);
	}

	public void deleteById(final Long productId) {
		ProductEntity productEntity = productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundProductException(productId));
		productRepository.deleteById(productEntity.getId());
	}
}
