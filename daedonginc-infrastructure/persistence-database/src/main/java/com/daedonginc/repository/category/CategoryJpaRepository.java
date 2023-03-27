package com.daedonginc.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daedonginc.entity.category.CategoryEntity;

/**
 * @author domo
 * Created on 2023/03/27
 */
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long>, CategoryRepository {
}
