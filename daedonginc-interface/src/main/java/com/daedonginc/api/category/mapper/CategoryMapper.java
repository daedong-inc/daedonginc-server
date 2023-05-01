package com.daedonginc.api.category.mapper;

import java.util.stream.Collectors;

import com.daedonginc.api.category.dto.CategoryResponseDto;
import com.daedonginc.category.domain.Category;

/**
 * @author domo
 * Created on 2023/04/03
 */
public class CategoryMapper {
	public static CategoryResponseDto toResponseDto(Category category) {
		return new CategoryResponseDto(
				category.id(),
				category.name(),
				category.level(),
				category.children().stream()
						.map(CategoryMapper::toResponseDto)
						.collect(Collectors.toList())
		);
	}
}
