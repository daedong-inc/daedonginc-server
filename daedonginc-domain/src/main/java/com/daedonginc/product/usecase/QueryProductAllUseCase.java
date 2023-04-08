package com.daedonginc.product.usecase;

import org.springframework.data.domain.Page;

import com.daedonginc.product.domain.Product;

/**
 * @author domo
 * Created on 2023/04/07
 */
public interface QueryProductAllUseCase {
	Page<Product> query(Query query);

	record Query(
			int page,
			int size
	) {
	}
}
