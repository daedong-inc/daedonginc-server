package com.daedonginc.category.usecase;

import java.util.List;

import com.daedonginc.category.domain.Category;

/**
 * @author domo
 * Created on 2023/04/03
 */
public interface QueryCategoryAllByParentIdUseCase {
	List<Category> query(Query query);

	record Query(
			Long ParentCategoryId
	) {
	}
}
