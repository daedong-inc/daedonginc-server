package com.daedonginc.category.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.category.usecase.CommandUpdateCategoryUseCase;
import com.daedonginc.service.category.CategoryCommand;

/**
 * @author domo
 * Created on 2023/04/04
 */
@Service
@Transactional
public class CommandUpdateCategory implements CommandUpdateCategoryUseCase {
	private final CategoryCommand categoryCommand;

	public CommandUpdateCategory(CategoryCommand categoryCommand) {
		this.categoryCommand = categoryCommand;
	}

	@Override
	public void command(Command command) {
		categoryCommand.update(command.categoryId(), command.parentId(), command.name());
	}
}
