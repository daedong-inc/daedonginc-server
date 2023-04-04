package com.daedonginc.category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.category.domain.Category;
import com.daedonginc.category.usecase.QueryCategoryAllUseCase;
import com.daedonginc.service.category.CategoryQuery;

/**
 * @author domo
 * Created on 2023/04/03
 */
@Service
@Transactional(readOnly = true)
public class QueryCategoryAll implements QueryCategoryAllUseCase {
	private final CategoryQuery categoryQuery;

	public QueryCategoryAll(final CategoryQuery categoryQuery) {
		this.categoryQuery = categoryQuery;
	}

	@Override
	public List<Category> query() {
		return categoryQuery.findAll().stream()
				.map(categoryEntity -> new Category(
						categoryEntity.getId(),
						categoryEntity.getName(),
						categoryEntity.getCategoryLevel().name(),
						categoryEntity.getChildren().stream()
								.map(child -> new Category(
										child.getId(),
										child.getName(),
										child.getCategoryLevel().name(),
										child.getChildren().stream()
												.map(grandChild -> new Category(
														grandChild.getId(),
														grandChild.getName(),
														grandChild.getCategoryLevel().name(),
														new ArrayList<>()))
												.collect(Collectors.toList())
								)).collect(Collectors.toList())
				)).collect(Collectors.toList());
	}
}