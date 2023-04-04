package com.daedonginc.category.usecase;

/**
 * @author domo
 * Created on 2023/04/04
 */
public interface CommandDeleteCategoryUseCase {
	void command(Command command);

	record Command(
			Long categoryId
	) {
	}
}
