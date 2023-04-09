package com.daedonginc.api.product;

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

import com.daedonginc.api.product.dto.CreateProductRequestDto;
import com.daedonginc.api.product.dto.ProductResponseDto;
import com.daedonginc.api.product.dto.UpdateProductRequestDto;
import com.daedonginc.api.product.mapper.ProductMapper;
import com.daedonginc.product.domain.Product;
import com.daedonginc.product.usecase.CommandCreateProductUseCase;
import com.daedonginc.product.usecase.CommandDeleteProductUseCase;
import com.daedonginc.product.usecase.CommandUpdateProductUseCase;
import com.daedonginc.product.usecase.QueryProductAllUseCase;
import com.daedonginc.product.usecase.QueryProductByIdUseCase;

/**
 * @author domo
 * Created on 2023/04/05
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	private final QueryProductAllUseCase queryProductAllUseCase;
	private final QueryProductByIdUseCase queryProductByIdUseCase;
	private final CommandCreateProductUseCase commandCreateProductUseCase;
	private final CommandUpdateProductUseCase commandUpdateProductUseCase;
	private final CommandDeleteProductUseCase commandDeleteProductUseCase;

	public ProductController(
			final QueryProductAllUseCase queryProductAllUseCase,
			final QueryProductByIdUseCase queryProductByIdUseCase,
			final CommandCreateProductUseCase commandCreateProductUseCase,
			final CommandUpdateProductUseCase commandUpdateProductUseCase,
			final CommandDeleteProductUseCase commandDeleteProductUseCase
	) {
		this.queryProductAllUseCase = queryProductAllUseCase;
		this.queryProductByIdUseCase = queryProductByIdUseCase;
		this.commandCreateProductUseCase = commandCreateProductUseCase;
		this.commandUpdateProductUseCase = commandUpdateProductUseCase;
		this.commandDeleteProductUseCase = commandDeleteProductUseCase;
	}

	@GetMapping
	public Page<ProductResponseDto> productAll(Pageable pageable) {
		return queryProductAllUseCase.query(
				new QueryProductAllUseCase.Query(pageable.getPageNumber(), pageable.getPageSize())
		).map(ProductMapper::toResponseDto);
	}

	@GetMapping("/{id}")
	public ProductResponseDto product(
			@PathVariable("id") Long id
	) {
		return ProductMapper.toResponseDto(
				queryProductByIdUseCase.query(
						new QueryProductByIdUseCase.Query(id)
				)
		);
	}

	@PostMapping
	public ProductResponseDto createProduct(
			@RequestBody @Validated final CreateProductRequestDto dto) {
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

	@PutMapping("/{id}")
	public void updateProduct(
			@PathVariable final Long id,
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
						dto.imageUrl()
				)
		);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(
			@PathVariable final Long id
	) {
		commandDeleteProductUseCase.command(
				new CommandDeleteProductUseCase.Command(id)
		);
	}
}