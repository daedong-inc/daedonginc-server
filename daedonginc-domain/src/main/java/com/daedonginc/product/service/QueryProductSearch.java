package com.daedonginc.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.product.domain.Product;
import com.daedonginc.product.usecase.QueryProductSearchUseCase;
import com.daedonginc.service.product.ProductQuery;

/**
 * @author domo
 * Created on 2023/04/07
 */
@Service
@Transactional(readOnly = true)
public class QueryProductSearch implements QueryProductSearchUseCase {
	private final ProductQuery productQuery;

	public QueryProductSearch(final ProductQuery productQuery) {
		this.productQuery = productQuery;
	}

	@Override
	public Page<Product> query(final Query query) {
		return productQuery.search(
				PageRequest.of(query.page(), query.size(), Sort.Direction.DESC, "id"),
				query.keyword(),
				query.parentId(),
				query.childId(),
				query.isHidden()
		).map(Product::from);
	}
}
