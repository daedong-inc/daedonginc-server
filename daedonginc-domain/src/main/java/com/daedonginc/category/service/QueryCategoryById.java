package com.daedonginc.category.service;

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
	public Category query(Query query) {
		return Category.from(categoryQuery.findById(query.ParentCategoryId()));
	}
}
