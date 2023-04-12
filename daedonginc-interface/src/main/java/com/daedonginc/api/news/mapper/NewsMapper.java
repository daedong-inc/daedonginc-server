package com.daedonginc.api.news.mapper;

import com.daedonginc.api.news.dto.NewsResponseDto;
import com.daedonginc.news.domain.News;

/**
 * @author domo
 * Created on 2023/04/11
 */
public class NewsMapper {
	public static NewsResponseDto toResponseDto(final News news) {
		return new NewsResponseDto(
				news.id(),
				news.newsType().name(),
				news.title(),
				news.content(),
				news.viewCount()
		);
	}
}
