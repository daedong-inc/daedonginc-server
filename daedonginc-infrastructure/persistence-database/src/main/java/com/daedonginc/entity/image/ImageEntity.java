package com.daedonginc.entity.image;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.daedonginc.config.BaseEntity;
import com.daedonginc.model.image.ImageCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author domo
 * Created on 2023/04/26
 */
@Entity
@Table(name = "image")
@Where(clause = "deleted = false")
@SQLDelete(sql = "update image set deleted = true where id = ?")
public class ImageEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ImageCategory imageCategory;

	private String uuid;
}
