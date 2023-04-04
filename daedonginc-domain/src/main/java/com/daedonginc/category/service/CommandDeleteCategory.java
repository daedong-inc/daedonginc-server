package com.daedonginc.category.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.category.usecase.CommandDeleteCategoryUseCase;
import com.daedonginc.service.category.CategoryCommand;

/**
 * @author domo
 * Created on 2023/04/04
 */
@Service
@Transactional
public class CommandDeleteCategory implements CommandDeleteCategoryUseCase {
	private final CategoryCommand categoryCommand;

	public CommandDeleteCategory(CategoryCommand categoryCommand) {
		this.categoryCommand = categoryCommand;
	}

	@Override
	public void command(Command command) {
		categoryCommand.deleteById(command.categoryId());
	}
}
