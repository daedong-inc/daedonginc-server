package com.daedonginc.category.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.category.domain.Category;
import com.daedonginc.category.usecase.CommandCreateCategoryUseCase;
import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.service.category.CategoryCommand;

/**
 * @author domo
 * Created on 2023/04/04
 */
@Service
@Transactional
public class CommandCreateCategory implements CommandCreateCategoryUseCase {
	private final CategoryCommand categoryCommand;

	public CommandCreateCategory(CategoryCommand categoryCommand) {
		this.categoryCommand = categoryCommand;
	}

	@Override
	public Category command(Command command) {
		CategoryEntity categoryEntity = categoryCommand.save(command.parentId(), command.name());
		return new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getCategoryLevel().name(),
				null);
	}
}
