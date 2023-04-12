package com.daedonginc.news.domain;

import com.daedonginc.model.news.NewsType;

/**
 * @author domo
 * Created on 2023/04/11
 */
public record News(
		long id,
		NewsType newsType,
		String title,
		String content,
		int viewCount
) {
}
