package com.daedonginc.product.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.daedonginc.category.domain.Category;
import com.daedonginc.entity.product.ProductEntity;

/**
 * @author domo
 * Created on 2023/04/05
 */
public record Product(
		long id,
		Category category,
		String name,
		int volume,
		String size,
		String partMaterial,
		String description,
		String imageUrl
) {
	public static Product from(ProductEntity productEntity) {
		return new Product(
				productEntity.getId(),
				new Category(
						productEntity.getCategory().getId(),
						productEntity.getCategory().getName(),
						productEntity.getCategory().getCategoryLevel().name(),
						productEntity.getCategory().getChildren().stream()
								.map(child -> new Category(
										child.getId(),
										child.getName(),
										child.getCategoryLevel().name(),
										new ArrayList<>()
								)).collect(Collectors.toList())
				),
				productEntity.getName(),
				productEntity.getVolume(),
				productEntity.getSize(),
				productEntity.getPartMaterial(),
				productEntity.getDescription(),
				productEntity.getImageUrl()
		);
	}
}
