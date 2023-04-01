package com.daedonginc.entity.popup;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.daedonginc.config.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author domo
 * Created on 2023/03/25
 */
@Entity
@Table(name = "popup")
@Where(clause = "deleted = false")
@SQLDelete(sql = "update popup set deleted = true where id = ?")
public class PopupEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String imageUrl;

	private String clickUrl;

	private int sort;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	protected PopupEntity() {
	}

	public PopupEntity(
			final String name,
			final String imageUrl,
			final String clickUrl,
			final int sort,
			final LocalDateTime startDate,
			final LocalDateTime endDate
	) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.clickUrl = clickUrl;
		this.sort = sort;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public static PopupEntity newInstance(
			final String name,
			final String imageUrl,
			final String clickUrl,
			final int sort,
			final LocalDateTime startDate,
			final LocalDateTime endDate
	) {
		return new PopupEntity(name, imageUrl, clickUrl, sort, startDate, endDate);
	}

	public void update(final String name, final String imageUrl, final String clickUrl, final int sort,
			final LocalDateTime startDate,
			final LocalDateTime endDate) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.clickUrl = clickUrl;
		this.sort = sort;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public int getSort() {
		return sort;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}
}
