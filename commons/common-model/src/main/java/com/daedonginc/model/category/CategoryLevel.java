package com.daedonginc.model.category;

/**
 * @author domo
 * Created on 2023/03/24
 */
public enum CategoryLevel {
	LARGE("대분류"),
	MIDDLE("중분류"),
	DETAIL("소분류");

	private final String description;

	CategoryLevel(String description) {
		this.description = description;
	}

	public CategoryLevel nextLevel() {
		if (this == LARGE) {
			return MIDDLE;
		}
		return DETAIL;
	}

	public String getDescription() {
		return description;
	}
}
