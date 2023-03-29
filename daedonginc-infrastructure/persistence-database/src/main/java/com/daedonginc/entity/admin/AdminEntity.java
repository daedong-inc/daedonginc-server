package com.daedonginc.entity.admin;

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
@Table(name = "admin")
@Where(clause = "deleted is false")
@SQLDelete(sql = "update admin set deleted = true where id = ?")
public class AdminEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String password;

	protected AdminEntity() {
	}

	private AdminEntity(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public static AdminEntity newInstance(String name, String password) {
		return new AdminEntity(name, password);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
