package com.daedonginc.repository.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.daedonginc.entity.product.ProductEntity;

/**
 * @author domo
 * Created on 2023/05/03
 */
public interface ProductRepositoryCustom {
	Page<ProductEntity> searchProducts(Pageable pageable, String keyword, Long parentId, Optional<Long> childId,
			boolean hidden);
}
