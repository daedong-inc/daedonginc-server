package com.daedonginc.product.usecase;

import java.util.Optional;

/**
 * @author domo
 * Created on 2023/04/05
 */
public interface CommandUpdateProductUseCase {
	void command(Command command);

	record Command(
			long productId,
			long categoryId,
			Optional<String> name,
			Optional<Integer> volume,
			Optional<String> size,
			Optional<String> partMaterial,
			Optional<String> description,
			Optional<String> imageUrl,
			Optional<Boolean> isHidden
	) {
	}
}
