package com.daedonginc.category.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.daedonginc.entity.category.CategoryEntity;

/**
 * @author domo
 * Created on 2023/04/03
 */
public record Category(
		long id,
		String name,
		String level,
		List<Category> children
) {
	public static Category from(CategoryEntity categoryEntity) {
		return new Category(
				categoryEntity.getId(),
				categoryEntity.getName(),
				categoryEntity.getCategoryLevel().name(),
				categoryEntity.getChildren().stream()
						.map(child -> new Category(
								child.getId(),
								child.getName(),
								child.getCategoryLevel().name(),
								new ArrayList<>()
						)).collect(Collectors.toList())
		);
	}

	@Override
	public List<Category> children() {
		if (children == null || children.isEmpty()) {
			return new ArrayList<>();
		}
		return children;
	}
}
