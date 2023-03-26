package com.daedonginc.config;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

/**
 * @author domo
 * Created on 2023/03/23
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	@Column(updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime lastModifiedAt;

	private boolean deleted = false;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getLastModifiedAt() {
		return lastModifiedAt;
	}

	public boolean isDeleted() {
		return deleted;
	}
}
