package com.daedonginc.service.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.entity.product.ProductEntity;
import com.daedonginc.model.product.CreateProduct;
import com.daedonginc.repository.category.CategoryRepository;
import com.daedonginc.repository.product.ProductRepository;
import com.daedonginc.service.category.exception.NotFoundCategoryException;
import com.daedonginc.service.product.exception.NotFoundProductException;

/**
 * @author domo
 * Created on 2023/03/25
 */
@Service
@Transactional
public class ProductCommand {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public ProductCommand(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public ProductEntity save(final CreateProduct createProduct) {
		CategoryEntity categoryEntity = categoryRepository.findById(createProduct.categoryId())
				.orElseThrow(() -> new NotFoundCategoryException(createProduct.categoryId()));

		ProductEntity productEntity = ProductEntity.newInstance(
				categoryEntity,
				createProduct.name(),
				createProduct.volume(),
				createProduct.size(),
				createProduct.partMaterial(),
				createProduct.description(),
				createProduct.imageUrl()
		);

		return productRepository.save(productEntity);
	}

	public void deleteById(final Long productId) {
		ProductEntity productEntity = productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundProductException(productId));
		productRepository.deleteById(productEntity.getId());
	}
}
