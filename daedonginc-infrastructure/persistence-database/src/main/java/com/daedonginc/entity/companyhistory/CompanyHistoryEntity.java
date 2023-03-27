package com.daedonginc.entity.companyhistory;

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
 * Created on 2023/03/23
 */
@Entity
@Table(name = "company_history")
@Where(clause = "deleted is false")
@SQLDelete(sql = "update company_history set deleted = true where id = ?")
public class CompanyHistoryEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int historyYear;

	private String content;

	protected CompanyHistoryEntity() {
	}

	private CompanyHistoryEntity(int historyYear, String content) {
		this.historyYear = historyYear;
		this.content = content;
	}

	public static CompanyHistoryEntity createComponyHistory(int historyYear, String content) {
		return new CompanyHistoryEntity(historyYear, content);
	}

	public Long getId() {
		return id;
	}

	public int getHistoryYear() {
		return historyYear;
	}

	public String getContent() {
		return content;
	}
}
