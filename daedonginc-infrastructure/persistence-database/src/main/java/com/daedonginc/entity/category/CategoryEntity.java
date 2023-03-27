package com.daedonginc.entity.category;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.daedonginc.config.BaseEntity;
import com.daedonginc.model.category.CategoryLevel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author domo
 * Created on 2023/03/24
 */
@Entity
@Table(name = "category")
@Where(clause = "deleted is false")
@SQLDelete(sql = "update category set deleted = true where id = ?")
public class CategoryEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private CategoryEntity parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private List<CategoryEntity> children;

	private String name;

	@Enumerated(EnumType.STRING)
	private CategoryLevel categoryLevel;

	protected CategoryEntity() {
	}

	public CategoryEntity(CategoryEntity parent, String name, CategoryLevel categoryLevel) {
		this.parent = parent;
		this.name = name;
		this.categoryLevel = categoryLevel;
	}

	public static CategoryEntity newParentInstance(String name, CategoryLevel categoryLevel) {
		return new CategoryEntity(null, name, categoryLevel);
	}

	public static CategoryEntity newChildrenInstance(CategoryEntity parent, String name, CategoryLevel categoryLevel) {
		return new CategoryEntity(parent, name, categoryLevel);
	}

	public Long getId() {
		return id;
	}

	public CategoryEntity getParent() {
		return parent;
	}

	public List<CategoryEntity> getChildren() {
		return children;
	}

	public String getName() {
		return name;
	}

	public CategoryLevel getCategoryLevel() {
		return categoryLevel;
	}
}
