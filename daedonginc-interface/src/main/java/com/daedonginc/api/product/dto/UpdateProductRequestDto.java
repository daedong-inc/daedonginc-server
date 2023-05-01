package com.daedonginc.api.product.dto;

import java.util.Optional;

import jakarta.validation.constraints.NotNull;

/**
 * @author domo
 * Created on 2023/04/05
 */
public record UpdateProductRequestDto(
		@NotNull(message = "카테고리ID는 필수입니다.")
		Long categoryId,
		Optional<String> name,
		Optional<Integer> volume,
		Optional<String> size,
		Optional<String> partMaterial,
		Optional<String> description,
		Optional<String> imageUrl,
		Optional<Boolean> isHidden
) {
}
