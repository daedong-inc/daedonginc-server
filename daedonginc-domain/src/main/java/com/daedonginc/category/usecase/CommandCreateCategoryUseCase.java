package com.daedonginc.category.usecase;

import com.daedonginc.category.domain.Category;

/**
 * @author domo
 * Created on 2023/04/04
 */
public interface CommandCreateCategoryUseCase {
	Category command(Command command);

	record Command(
			Long parentId,
			String name
	) {
	}
}
