package com.daedonginc.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daedonginc.entity.product.ProductEntity;

/**
 * @author domo
 * Created on 2023/03/25
 */
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long>, ProductRepository {
}
