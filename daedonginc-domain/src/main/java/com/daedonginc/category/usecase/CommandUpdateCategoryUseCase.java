package com.daedonginc.category.usecase;

/**
 * @author domo
 * Created on 2023/04/04
 */
public interface CommandUpdateCategoryUseCase {
	void command(Command command);

	record Command(
			Long categoryId,
			Long parentId,
			String name
	) {
	}
}
