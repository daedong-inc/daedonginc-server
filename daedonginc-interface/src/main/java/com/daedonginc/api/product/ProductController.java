package com.daedonginc.api.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.daedonginc.api.product.dto.CreateProductRequestDto;
import com.daedonginc.api.product.dto.ProductResponseDto;
import com.daedonginc.api.product.dto.UpdateProductRequestDto;
import com.daedonginc.api.product.mapper.ProductMapper;
import com.daedonginc.product.domain.Product;
import com.daedonginc.product.usecase.CommandCreateProductUseCase;
import com.daedonginc.product.usecase.CommandDeleteProductUseCase;
import com.daedonginc.product.usecase.CommandUpdateProductUseCase;
import com.daedonginc.product.usecase.QueryProductAllByChildIdUseCase;
import com.daedonginc.product.usecase.QueryProductAllByParentIdUseCase;
import com.daedonginc.product.usecase.QueryProductByIdUseCase;
import com.daedonginc.product.usecase.QueryProductSearchUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author domo
 * Created on 2023/04/05
 */
@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "Product", description = "상품")
public class ProductController {
	private final QueryProductSearchUseCase queryProductSearchUseCase;
	private final QueryProductAllByParentIdUseCase queryProductAllByParentIdUseCase;
	private final QueryProductAllByChildIdUseCase queryProductAllByChildIdUseCase;
	private final QueryProductByIdUseCase queryProductByIdUseCase;
	private final CommandCreateProductUseCase commandCreateProductUseCase;
	private final CommandUpdateProductUseCase commandUpdateProductUseCase;
	private final CommandDeleteProductUseCase commandDeleteProductUseCase;

	public ProductController(
			final QueryProductSearchUseCase queryProductSearchUseCase,
			final QueryProductAllByParentIdUseCase queryProductAllByParentIdUseCase,
			final QueryProductAllByChildIdUseCase queryProductAllByChildIdUseCase,
			final QueryProductByIdUseCase queryProductByIdUseCase,
			final CommandCreateProductUseCase commandCreateProductUseCase,
			final CommandUpdateProductUseCase commandUpdateProductUseCase,
			final CommandDeleteProductUseCase commandDeleteProductUseCase
	) {
		this.queryProductSearchUseCase = queryProductSearchUseCase;
		this.queryProductAllByParentIdUseCase = queryProductAllByParentIdUseCase;
		this.queryProductAllByChildIdUseCase = queryProductAllByChildIdUseCase;
		this.queryProductByIdUseCase = queryProductByIdUseCase;
		this.commandCreateProductUseCase = commandCreateProductUseCase;
		this.commandUpdateProductUseCase = commandUpdateProductUseCase;
		this.commandDeleteProductUseCase = commandDeleteProductUseCase;
	}

	@GetMapping
	@Operation(summary = "상품 검색", description = "page, size, keyword, parentId, childId, isHidden 값들을 입력받아 검색합니다.")
	public Page<ProductResponseDto> searchProducts(
			@Parameter(name = "page", description = "페이지 번호 *(0부터 시작)") int page,
			@Parameter(name = "size", description = "사이즈") int size,
			@Parameter(name = "keyword", description = "검색어") String keyword,
			@Parameter(name = "parentId", description = "부모 카테고리 ID") Long parentId,
			@Parameter(name = "childId", description = "자식 카테고리 ID") Optional<Long> childId,
			@Parameter(name = "비공개 여부", description = "공개=false, 비공개=true") boolean isHidden
	) {
		return queryProductSearchUseCase.query(
				new QueryProductSearchUseCase.Query(
						page,
						size,
						keyword,
						parentId,
						childId,
						isHidden
				)).map(ProductMapper::toResponseDto);
	}

	@GetMapping("/parent/{parentId}")
	public Page<ProductResponseDto> productAllByParentCategoryId(
			Pageable pageable,
			@PathVariable Long parentId
	) {
		return queryProductAllByParentIdUseCase.query(
				new QueryProductAllByParentIdUseCase.Query(pageable.getPageNumber(), pageable.getPageSize(), parentId)
		).map(ProductMapper::toResponseDto);
	}

	@GetMapping("/child/{childId}")
	public Page<ProductResponseDto> productAllByChildCategoryId(
			Pageable pageable,
			@PathVariable Long childId
	) {
		return queryProductAllByChildIdUseCase.query(
				new QueryProductAllByChildIdUseCase.Query(pageable.getPageNumber(), pageable.getPageSize(), childId)
		).map(ProductMapper::toResponseDto);
	}

	@GetMapping("/{id}")
	@Operation(summary = "상품 상세 조회", description = "상품 ID를 받아 상세 정보를 검색합니다.")
	public ProductResponseDto product(
			@Parameter(name = "id", description = "상품 ID") @PathVariable("id") Long id
	) {
		return ProductMapper.toResponseDto(
				queryProductByIdUseCase.query(
						new QueryProductByIdUseCase.Query(id)
				)
		);
	}

	@AdminLoginCheck
	@PostMapping
	@GetMapping("/{id}")
	@Operation(summary = "상품 등록", description = "상품 정보를 입력받아 등록합니다.")
	public ProductResponseDto createProduct(
			@RequestBody @Validated final CreateProductRequestDto dto
	) {
		Product product = commandCreateProductUseCase.command(
				new CommandCreateProductUseCase.Command(
						dto.categoryId(),
						dto.name(),
						dto.volume(),
						dto.size(),
						dto.partMaterial(),
						dto.description(),
						dto.imageUrl()
				)
		);

		return ProductMapper.toResponseDto(product);
	}

	@AdminLoginCheck
	@PutMapping("/{id}")
	@Operation(summary = "상품 수정", description = "상품 ID와 수정 할 상품의 내용만 받아 수정합니다.")
	public void updateProduct(
			@Parameter(name = "id", description = "상품 ID") @PathVariable("id") Long id,
			@RequestBody @Validated final UpdateProductRequestDto dto
	) {
		commandUpdateProductUseCase.command(
				new CommandUpdateProductUseCase.Command(
						id,
						dto.categoryId(),
						dto.name(),
						dto.volume(),
						dto.size(),
						dto.partMaterial(),
						dto.description(),
						dto.imageUrl(),
						dto.isHidden()
				)
		);
	}

	@AdminLoginCheck
	@DeleteMapping("/{id}")
	@Operation(summary = "상품 삭제", description = "상품 ID를 받아 삭제합니다.")
	public void deleteProduct(
			@Parameter(name = "id", description = "상품 ID") @PathVariable("id") Long id
	) {
		commandDeleteProductUseCase.command(
				new CommandDeleteProductUseCase.Command(id)
		);
	}
}
