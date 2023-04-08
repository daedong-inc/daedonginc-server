package com.daedonginc.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.product.domain.Product;
import com.daedonginc.product.usecase.QueryProductByIdUseCase;
import com.daedonginc.service.product.ProductQuery;

/**
 * @author domo
 * Created on 2023/04/07
 */
@Service
@Transactional(readOnly = true)
public class QueryProductById implements QueryProductByIdUseCase {
	private final ProductQuery productQuery;

	public QueryProductById(final ProductQuery productQuery) {
		this.productQuery = productQuery;
	}

	@Override
	public Product query(final Query query) {
		return Product.from(productQuery.findById(query.productId()));
	}
}
