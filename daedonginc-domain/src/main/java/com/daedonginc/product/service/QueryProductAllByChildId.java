package com.daedonginc.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.product.domain.Product;
import com.daedonginc.product.usecase.QueryProductAllByChildIdUseCase;
import com.daedonginc.service.product.ProductQuery;

/**
 * @author domo
 * Created on 2023/04/26
 */
@Service
@Transactional(readOnly = true)
public class QueryProductAllByChildId implements QueryProductAllByChildIdUseCase {
	private final ProductQuery productQuery;

	public QueryProductAllByChildId(final ProductQuery productQuery) {
		this.productQuery = productQuery;
	}

	@Override
	public Page<Product> query(final Query query) {
		List<Product> productList = productQuery.findAllByChildId(
						PageRequest.of(query.page(), query.size(), Sort.Direction.DESC, "id"),
						query.childId()
				).stream()
				.map(productEntity -> Product.from(productEntity))
				.collect(Collectors.toList());

		long total = productQuery.countByChildId(query.childId());

		PageImpl<Product> products = new PageImpl<>(productList, PageRequest.of(query.page(), query.size()), total);
		return products;
	}
}
