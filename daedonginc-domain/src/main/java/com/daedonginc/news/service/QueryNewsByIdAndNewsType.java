package com.daedonginc.news.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.news.NewsEntity;
import com.daedonginc.news.domain.News;
import com.daedonginc.news.usecase.QueryNewsByIdAndNewsTypeUseCase;
import com.daedonginc.service.news.NewsQuery;

/**
 * @author domo
 * Created on 2023/04/11
 */
@Service
@Transactional
public class QueryNewsByIdAndNewsType implements QueryNewsByIdAndNewsTypeUseCase {
	private final NewsQuery newsQuery;

	public QueryNewsByIdAndNewsType(final NewsQuery newsQuery) {
		this.newsQuery = newsQuery;
	}

	@Override
	public News query(final Query query) {
		NewsEntity newsEntity = newsQuery.findByIdAndNewsType(query.id(), query.newsType());
		newsEntity.view();
		return new News(
				newsEntity.getId(),
				newsEntity.getNewsType(),
				newsEntity.getTitle(),
				newsEntity.getContent(),
				newsEntity.getViewCount()
		);
	}
}
