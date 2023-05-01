package com.daedonginc.api.product.dto;

import com.daedonginc.category.domain.Category;

/**
 * @author domo
 * Created on 2023/04/05
 */
public record ProductResponseDto(
		long id,
		Category category,
		String name,
		int volume,
		String size,
		String partMaterial,
		String description,
		String imageUrl,
		boolean isHidden
) {
}
