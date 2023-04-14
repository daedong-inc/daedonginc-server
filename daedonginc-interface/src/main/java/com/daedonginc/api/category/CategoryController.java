package com.daedonginc.api.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daedonginc.aop.AdminLoginCheck;
import com.daedonginc.api.category.dto.CategoryResponseDto;
import com.daedonginc.api.category.dto.CreateCategoryRequestDto;
import com.daedonginc.api.category.dto.UpdateCategoryRequestDto;
import com.daedonginc.api.category.mapper.CategoryMapper;
import com.daedonginc.category.domain.Category;
import com.daedonginc.category.usecase.CommandCreateCategoryUseCase;
import com.daedonginc.category.usecase.CommandDeleteCategoryUseCase;
import com.daedonginc.category.usecase.CommandUpdateCategoryUseCase;
import com.daedonginc.category.usecase.QueryCategoryAllByParentIdUseCase;
import com.daedonginc.category.usecase.QueryCategoryAllUseCase;

/**
 * @author domo
 * Created on 2023/04/03
 */
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	private final QueryCategoryAllByParentIdUseCase queryCategoryAllByParentIdUseCase;
	private final QueryCategoryAllUseCase queryCategoryAllUseCase;
	private final CommandCreateCategoryUseCase commandCreateCategoryUseCase;
	private final CommandUpdateCategoryUseCase commandUpdateCategoryUseCase;
	private final CommandDeleteCategoryUseCase commandDeleteCategoryUseCase;

	public CategoryController(
			final QueryCategoryAllByParentIdUseCase queryCategoryAllByParentIdUseCase,
			final QueryCategoryAllUseCase queryCategoryAllUseCase,
			final CommandCreateCategoryUseCase commandCreateCategoryUseCase,
			final CommandUpdateCategoryUseCase commandUpdateCategoryUseCase,
			final CommandDeleteCategoryUseCase commandDeleteCategoryUseCase
	) {
		this.queryCategoryAllByParentIdUseCase = queryCategoryAllByParentIdUseCase;
		this.queryCategoryAllUseCase = queryCategoryAllUseCase;
		this.commandCreateCategoryUseCase = commandCreateCategoryUseCase;
		this.commandUpdateCategoryUseCase = commandUpdateCategoryUseCase;
		this.commandDeleteCategoryUseCase = commandDeleteCategoryUseCase;
	}

	@GetMapping
	public List<CategoryResponseDto> categoryAll() {
		return queryCategoryAllUseCase.query().stream()
				.map(CategoryMapper::toResponseDto)
				.collect(Collectors.toList());
	}

	@GetMapping("/{parentId}")
	public List<CategoryResponseDto> categoryAllByParentId(@PathVariable final Long parentId) {
		return queryCategoryAllByParentIdUseCase.query(
						new QueryCategoryAllByParentIdUseCase.Query(parentId)).stream()
				.map(CategoryMapper::toResponseDto)
				.collect(Collectors.toList());
	}

	@AdminLoginCheck
	@PostMapping
	public CategoryResponseDto createCategory(
			@RequestBody @Validated final CreateCategoryRequestDto createCategoryRequestDto
	) {
		Category category = commandCreateCategoryUseCase.command(
				new CommandCreateCategoryUseCase.Command(
						createCategoryRequestDto.parentId(),
						createCategoryRequestDto.name()
				)
		);
		return CategoryMapper.toResponseDto(category);
	}

	@AdminLoginCheck
	@PutMapping("/{id}")
	public void updateCategory(
			@PathVariable final Long id,
			@RequestBody @Validated final UpdateCategoryRequestDto updateCategoryRequestDto
	) {
		commandUpdateCategoryUseCase.command(
				new CommandUpdateCategoryUseCase.Command(
						id,
						updateCategoryRequestDto.parentId(),
						updateCategoryRequestDto.name()
				)
		);
	}

	@AdminLoginCheck
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable final Long id) {
		commandDeleteCategoryUseCase.command(
				new CommandDeleteCategoryUseCase.Command(id)
		);
	}

}
