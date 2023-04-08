package com.daedonginc.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.entity.product.ProductEntity;
import com.daedonginc.model.category.CategoryLevel;
import com.daedonginc.product.domain.Product;
import com.daedonginc.product.exception.CategoryLevelIsNotSuitableException;
import com.daedonginc.product.usecase.CommandCreateProductUseCase;
import com.daedonginc.service.category.CategoryQuery;
import com.daedonginc.service.product.ProductCommand;

/**
 * @author domo
 * Created on 2023/04/05
 */
@Service
@Transactional
public class CommandCreateProduct implements CommandCreateProductUseCase {
	private final ProductCommand productCommand;
	private final CategoryQuery categoryQuery;

	public CommandCreateProduct(
			final ProductCommand productCommand,
			final CategoryQuery categoryQuery
	) {
		this.productCommand = productCommand;
		this.categoryQuery = categoryQuery;
	}

	@Override
	public Product command(final Command command) {
		CategoryEntity categoryEntity = categoryQuery.findById(command.categoryId());

		if (categoryEntity.getCategoryLevel() != CategoryLevel.MIDDLE) {
			throw new CategoryLevelIsNotSuitableException("하위 카테고리만 등록 가능합니다.");
		}

		ProductEntity productEntity = productCommand.save(
				ProductEntity.newInstance(
						categoryEntity,
						command.name(),
						command.volume(),
						command.size(),
						command.partMaterial(),
						command.description(),
						command.imageUrl()
				)
		);

		return Product.from(productEntity);
	}
}
