package com.daedonginc.api.product.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author domo
 * Created on 2023/04/05
 */
public record CreateProductRequestDto(
		@NotNull(message = "카테고리ID는 필수입니다.")
		Long categoryId,
		@NotEmpty(message = "상품명은 필수입니다.")
		String name,
		@NotNull(message = "용량은 필수입니다.")
		Integer volume,
		@NotEmpty(message = "사이즈는 필수입니다.")
		String size,
		@NotEmpty(message = "부품재질은 필수입니다.")
		String partMaterial,
		@NotEmpty(message = "상품설명은 필수입니다.")
		String description,
		@NotEmpty(message = "이미지URL은 필수입니다.")
		String imageUrl
) {
}
