package com.daedonginc.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.model.category.CategoryLevel;
import com.daedonginc.product.exception.CategoryLevelIsNotSuitableException;
import com.daedonginc.product.usecase.CommandUpdateProductUseCase;
import com.daedonginc.service.category.CategoryQuery;
import com.daedonginc.service.product.ProductQuery;

/**
 * @author domo
 * Created on 2023/04/05
 */
@Service
@Transactional
public class CommandUpdateProduct implements CommandUpdateProductUseCase {
	private final ProductQuery productQuery;
	private final CategoryQuery categoryQuery;

	public CommandUpdateProduct(
			final ProductQuery productQuery,
			final CategoryQuery categoryQuery
	) {
		this.productQuery = productQuery;
		this.categoryQuery = categoryQuery;
	}

	@Override
	public void command(final Command command) {
		CategoryEntity categoryEntity = categoryQuery.findById(command.categoryId());

		if (categoryEntity.getCategoryLevel() != CategoryLevel.MIDDLE) {
			throw new CategoryLevelIsNotSuitableException("하위 카테고리만 등록 가능합니다.");
		}

		productQuery.findById(command.productId())
				.update(
						categoryEntity,
						command.name(),
						command.volume(),
						command.size(),
						command.partMaterial(),
						command.description(),
						command.imageUrl(),
						command.isHidden()
				);
	}
}
