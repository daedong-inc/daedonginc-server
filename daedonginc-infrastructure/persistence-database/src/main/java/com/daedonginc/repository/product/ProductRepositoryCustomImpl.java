package com.daedonginc.repository.product;

import static com.daedonginc.entity.category.QCategoryEntity.*;
import static com.daedonginc.entity.product.QProductEntity.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.entity.product.ProductEntity;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * @author domo
 * Created on 2023/05/03
 */
@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	public ProductRepositoryCustomImpl(final JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	@Override
	public Page<ProductEntity> searchProducts(
			Pageable pageable,
			String keyword,
			Long parentId,
			Optional<Long> childId,
			boolean hidden
	) {
		if (childId.isEmpty()) {
			List<Long> childCateogryIdList = jpaQueryFactory.selectFrom(categoryEntity)
					.where(categoryEntity.id.eq(parentId))
					.fetchOne()
					.getChildren()
					.stream().map(CategoryEntity::getId)
					.collect(Collectors.toList());

			QueryResults<ProductEntity> results = jpaQueryFactory
					.selectFrom(productEntity)
					.where(
							productEntity.isHidden.eq(hidden)
									.and(productEntity.name.containsIgnoreCase(keyword))
									.and(productEntity.category.id.in(childCateogryIdList))
					)
					.offset(pageable.getOffset())
					.limit(pageable.getPageSize())
					.fetchResults();
			return new PageImpl<>(results.getResults(), pageable, results.getTotal());
		} else {
			QueryResults<ProductEntity> results = jpaQueryFactory
					.selectFrom(productEntity)
					.where(
							productEntity.isHidden.eq(hidden)
									.and(productEntity.name.containsIgnoreCase(keyword))
									.and(productEntity.category.id.eq(childId.get()))
					)
					.offset(pageable.getOffset())
					.limit(pageable.getPageSize())
					.fetchResults();
			return new PageImpl<>(results.getResults(), pageable, results.getTotal());
		}
	}
}
