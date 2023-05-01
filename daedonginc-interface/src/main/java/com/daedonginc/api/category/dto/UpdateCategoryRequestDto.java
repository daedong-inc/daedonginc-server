package com.daedonginc.api.category.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author domo
 * Created on 2023/04/02
 */
public record UpdateCategoryRequestDto(
		Long parentId,
		@NotEmpty(message = "카테고리 명은 필수입니다.")
		String name
) {
}
