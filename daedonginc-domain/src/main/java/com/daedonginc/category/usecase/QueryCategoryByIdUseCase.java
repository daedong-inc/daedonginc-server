package com.daedonginc.category.usecase;

import com.daedonginc.category.domain.Category;

/**
 * @author domo
 * Created on 2023/04/03
 */
public interface QueryCategoryByIdUseCase {
	Category query(Query query);

	record Query(
			Long ParentCategoryId
	) {
	}
}
