package com.daedonginc.api.news.dto;

/**
 * @author domo
 * Created on 2023/04/11
 */
public record NewsResponseDto(
		long id,
		String newsType,
		String title,
		String content,
		int viewCount
) {
}
