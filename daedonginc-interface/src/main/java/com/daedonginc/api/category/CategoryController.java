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
import com.daedonginc.category.usecase.QueryCategoryAllUseCase;
import com.daedonginc.category.usecase.QueryCategoryByIdUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author domo
 * Created on 2023/04/03
 */
@RestController
@RequestMapping("/api/v1/category")
@Tag(name = "Category", description = "카테고리")
public class CategoryController {
	private final QueryCategoryByIdUseCase queryCategoryByIdUseCase;
	private final QueryCategoryAllUseCase queryCategoryAllUseCase;
	private final CommandCreateCategoryUseCase commandCreateCategoryUseCase;
	private final CommandUpdateCategoryUseCase commandUpdateCategoryUseCase;
	private final CommandDeleteCategoryUseCase commandDeleteCategoryUseCase;

	public CategoryController(
			final QueryCategoryByIdUseCase queryCategoryByIdUseCase,
			final QueryCategoryAllUseCase queryCategoryAllUseCase,
			final CommandCreateCategoryUseCase commandCreateCategoryUseCase,
			final CommandUpdateCategoryUseCase commandUpdateCategoryUseCase,
			final CommandDeleteCategoryUseCase commandDeleteCategoryUseCase
	) {
		this.queryCategoryByIdUseCase = queryCategoryByIdUseCase;
		this.queryCategoryAllUseCase = queryCategoryAllUseCase;
		this.commandCreateCategoryUseCase = commandCreateCategoryUseCase;
		this.commandUpdateCategoryUseCase = commandUpdateCategoryUseCase;
		this.commandDeleteCategoryUseCase = commandDeleteCategoryUseCase;
	}

	@GetMapping
	@Operation(summary = "카테고리 전체 조회", description = "카테고리를 전체 조회합니다")
	public List<CategoryResponseDto> categoryAll() {
		return queryCategoryAllUseCase.query().stream()
				.map(CategoryMapper::toResponseDto)
				.collect(Collectors.toList());
	}

	@GetMapping("/{categoryId}")
	@Operation(summary = "카테고리 조회", description = "카테고리를 조회합니다")
	public CategoryResponseDto categoryById(
			@Parameter(description = "카테고리 아이디", required = true) @PathVariable final Long categoryId
	) {
		Category category = queryCategoryByIdUseCase.query(
				new QueryCategoryByIdUseCase.Query(categoryId)
		);
		return CategoryMapper.toResponseDto(category);
	}

	@AdminLoginCheck
	@PostMapping
	@Operation(summary = "카테고리 생성", description = "카테고리를 생성합니다")
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
	@Operation(summary = "카테고리 수정", description = "카테고리를 수정합니다")
	public void updateCategory(
			@Parameter(description = "카테고리 아이디", required = true) @PathVariable final Long id,
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
	@Operation(summary = "카테고리 삭제", description = "카테고리를 삭제합니다")
	public void deleteCategory(
			@Parameter(description = "카테고리 아이디", required = true) @PathVariable final Long id
	) {
		commandDeleteCategoryUseCase.command(
				new CommandDeleteCategoryUseCase.Command(id)
		);
	}

}
