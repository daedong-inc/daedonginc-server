package com.daedonginc.news.usecase;

import org.springframework.data.domain.Page;

import com.daedonginc.model.news.NewsType;
import com.daedonginc.news.domain.News;

/**
 * @author domo
 * Created on 2023/04/11
 */
public interface QueryNewsAllByNewsTypeUseCase {
	Page<News> query(Query query);

	record Query(
			int page,
			int size,
			NewsType newsType
	) {
	}
}
