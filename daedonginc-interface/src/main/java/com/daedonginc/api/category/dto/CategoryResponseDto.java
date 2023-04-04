package com.daedonginc.api.category.dto;

import java.util.List;

/**
 * @author domo
 * Created on 2023/04/03
 */
public record CategoryResponseDto(
		long id,
		String name,
		String level,
		List<CategoryResponseDto> children
) {
}
