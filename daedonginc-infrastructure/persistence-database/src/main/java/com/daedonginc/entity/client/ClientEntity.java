package com.daedonginc.entity.client;

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
@Table(name = "client")
@Where(clause = "deleted = false")
@SQLDelete(sql = "update client set deleted = true where id = ?")
public class ClientEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String imageUrl;

	private String clickUrl;

	private int sort;

	protected ClientEntity() {
	}

	private ClientEntity(final String name, final String imageUrl, final String clickUrl, final int sort) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.clickUrl = clickUrl;
		this.sort = sort;
	}

	public static ClientEntity newInstance(final String name, final String imageUrl, final String clickUrl, int sort) {
		return new ClientEntity(name, imageUrl, clickUrl, sort);
	}

	public void update(final String name, final String imageUrl, final String clickUrl, final int sort) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.clickUrl = clickUrl;
		this.sort = sort;
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
}
