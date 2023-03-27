package com.daedonginc.model.news;

/**
 * @author domo
 * Created on 2023/03/25
 */
public enum NewsType {
	PRODUCT_INFORMATION("제품 소식"),
	NOTICE("공지사항");

	private final String description;

	NewsType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
