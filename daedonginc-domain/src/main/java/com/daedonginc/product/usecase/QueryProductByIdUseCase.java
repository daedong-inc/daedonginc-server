package com.daedonginc.product.usecase;

import com.daedonginc.product.domain.Product;

/**
 * @author domo
 * Created on 2023/04/07
 */
public interface QueryProductByIdUseCase {
	Product query(Query query);

	record Query(
			Long productId
	) {
	}
}
