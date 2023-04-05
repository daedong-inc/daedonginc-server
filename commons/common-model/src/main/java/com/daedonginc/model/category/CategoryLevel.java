package com.daedonginc.model.category;

/**
 * @author domo
 * Created on 2023/03/24
 */
public enum CategoryLevel {
	LARGE("대분류"),
	MIDDLE("중분류");

	private final String description;

	CategoryLevel(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
