package com.daedonginc.api.product.mapper;

import com.daedonginc.api.product.dto.ProductResponseDto;
import com.daedonginc.product.domain.Product;

/**
 * @author domo
 * Created on 2023/04/05
 */
public class ProductMapper {
	public static ProductResponseDto toResponseDto(final Product product) {
		return new ProductResponseDto(
				product.id(),
				product.category(),
				product.name(),
				product.volume(),
				product.size(),
				product.partMaterial(),
				product.description(),
				product.imageUrl(),
				product.isHidden()
		);
	}
}
