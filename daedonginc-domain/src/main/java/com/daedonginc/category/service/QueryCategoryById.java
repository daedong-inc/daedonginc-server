package com.daedonginc.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.category.domain.Category;
import com.daedonginc.category.usecase.QueryCategoryByIdUseCase;
import com.daedonginc.service.category.CategoryQuery;

/**
 * @author domo
 * Created on 2023/04/03
 */
@Service
@Transactional(readOnly = true)
public class QueryCategoryById implements QueryCategoryByIdUseCase {
	private final CategoryQuery categoryQuery;

	public QueryCategoryById(final CategoryQuery categoryQuery) {
		this.categoryQuery = categoryQuery;
	}

	@Override
	public List<Category> query(Query query) {
		return categoryQuery.findAllByParentId(query.ParentCategoryId()).stream()
				.map(categoryEntity -> Category.from(categoryEntity))
				.collect(Collectors.toList());
	}
}
