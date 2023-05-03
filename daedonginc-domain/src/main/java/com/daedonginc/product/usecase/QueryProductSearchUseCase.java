package com.daedonginc.product.usecase;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.daedonginc.product.domain.Product;

/**
 * @author domo
 * Created on 2023/04/07
 */
public interface QueryProductSearchUseCase {
	Page<Product> query(Query query);

	record Query(
			int page,
			int size,
			String keyword,
			Long parentId,
			Optional<Long> childId,
			boolean isHidden
	) {
	}
}
