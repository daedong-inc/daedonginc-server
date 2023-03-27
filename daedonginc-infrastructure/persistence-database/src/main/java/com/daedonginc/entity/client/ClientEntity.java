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
@Where(clause = "deleted is false")
@SQLDelete(sql = "update client set deleted = true where id = ?")
public class ClientEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String imageUrl;

	private String clickUrl;

	protected ClientEntity() {
	}

	private ClientEntity(String name, String imageUrl, String clickUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.clickUrl = clickUrl;
	}

	public static ClientEntity newInstance(String name, String imageUrl, String clickUrl) {
		return new ClientEntity(name, imageUrl, clickUrl);
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
}
