package com.daedonginc.product.usecase;

/**
 * @author domo
 * Created on 2023/04/05
 */
public interface CommandUpdateProductUseCase {
	void command(Command command);

	record Command(
			long productId,
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
