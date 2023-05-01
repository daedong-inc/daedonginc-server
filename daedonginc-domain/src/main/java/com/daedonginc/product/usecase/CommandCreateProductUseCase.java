package com.daedonginc.product.usecase;

import com.daedonginc.product.domain.Product;

/**
 * @author domo
 * Created on 2023/04/05
 */
public interface CommandCreateProductUseCase {
	Product command(Command command);

	record Command(
			long categoryId,
			String name,
			int volume,
			String size,
			String partMaterial,
			String description,
			String imageUrl
	) {
	}
}
