package com.daedonginc.repository.category;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.daedonginc.entity.category.CategoryEntity;
import com.daedonginc.model.category.CategoryLevel;

/**
 * @author domo
 * Created on 2023/03/27
 */
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long>, CategoryRepository {
	List<CategoryEntity> findAllByCategoryLevel(CategoryLevel level);

	@EntityGraph(attributePaths = "children")
	List<CategoryEntity> findAllByParentIsNull();
}
