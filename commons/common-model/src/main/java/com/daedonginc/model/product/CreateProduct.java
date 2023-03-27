package com.daedonginc.model.product;

/**
 * @author domo
 * Created on 2023/03/27
 */
public record CreateProduct(
		Long categoryId,
		String name,
		int volume,
		String size,
		String partMaterial,
		String description,
		String imageUrl
) {
}
