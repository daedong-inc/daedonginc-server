package com.daedonginc.entity.news;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.daedonginc.config.BaseEntity;
import com.daedonginc.model.news.NewsType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author domo
 * Created on 2023/03/25
 */
@Entity
@Table(name = "news")
@Where(clause = "deleted = false")
@SQLDelete(sql = "update news set deleted = true where id = ?")
public class NewsEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private NewsType newsType;

	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	private int viewCount;

	protected NewsEntity() {
	}

	private NewsEntity(NewsType newsType, String title, String content) {
		this.newsType = newsType;
		this.title = title;
		this.content = content;
	}

	public static NewsEntity newInstance(NewsType newsType, String title, String content) {
		return new NewsEntity(newsType, title, content);
	}

	public NewsEntity view() {
		this.viewCount++;
		return this;
	}

	public Long getId() {
		return id;
	}

	public NewsType getNewsType() {
		return newsType;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public int getViewCount() {
		return viewCount;
	}
}
