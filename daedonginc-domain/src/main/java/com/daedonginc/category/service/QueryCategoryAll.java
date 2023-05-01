package com.daedonginc.category.service;

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
				.map(categoryEntity -> Category.from(categoryEntity))
				.collect(Collectors.toList());
	}
}
