package com.daedonginc.entity.product;

import java.util.Optional;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.daedonginc.config.BaseEntity;
import com.daedonginc.entity.category.CategoryEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author domo
 * Created on 2023/03/24
 */
@Entity
@Table(name = "product")
@Where(clause = "deleted = false")
@SQLDelete(sql = "update product set deleted = true where id = ?")
public class ProductEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category;

	private String name;

	private int volume;

	private String size;

	private String partMaterial;

	private String description;

	private String imageUrl;

	private boolean isHidden = false;

	protected ProductEntity() {
	}

	private ProductEntity(CategoryEntity category, String name, int volume, String size, String partMaterial,
			String description, String imageUrl) {
		this.category = category;
		this.name = name;
		this.volume = volume;
		this.size = size;
		this.partMaterial = partMaterial;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public static ProductEntity newInstance(CategoryEntity category, String name, int volume, String size,
			String partMaterial, String description, String imageUrl) {
		return new ProductEntity(category, name, volume, size, partMaterial, description, imageUrl);
	}

	public void update(
			final CategoryEntity categoryEntity,
			final Optional<String> name,
			final Optional<Integer> volume,
			final Optional<String> size,
			final Optional<String> partMaterial,
			final Optional<String> description,
			final Optional<String> imageUrl,
			final Optional<Boolean> isHidden
	) {
		this.category = categoryEntity;
		this.name = name.orElseGet(this::getName);
		this.volume = volume.orElseGet(this::getVolume);
		this.size = size.orElseGet(this::getSize);
		this.partMaterial = partMaterial.orElseGet(this::getPartMaterial);
		this.description = description.orElseGet(this::getDescription);
		this.imageUrl = imageUrl.orElseGet(this::getImageUrl);
		this.isHidden = isHidden.orElseGet(this::isHidden);
	}

	public Long getId() {
		return id;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public int getVolume() {
		return volume;
	}

	public String getSize() {
		return size;
	}

	public String getPartMaterial() {
		return partMaterial;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public boolean isHidden() {
		return isHidden;
	}
}
