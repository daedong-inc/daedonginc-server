package com.daedonginc.news.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.news.domain.News;
import com.daedonginc.news.usecase.QueryNewsAllByNewsTypeUseCase;
import com.daedonginc.service.news.NewsQuery;

/**
 * @author domo
 * Created on 2023/04/11
 */
@Service
@Transactional(readOnly = true)
public class QueryNewsAllByNewsType implements QueryNewsAllByNewsTypeUseCase {
	private final NewsQuery newsQuery;

	public QueryNewsAllByNewsType(final NewsQuery newsQuery) {
		this.newsQuery = newsQuery;
	}

	@Override
	public Page<News> query(final Query query) {
		List<News> content = newsQuery.findAllByNewsType(
						PageRequest.of(query.page(), query.size(), Sort.Direction.DESC, "id"),
						query.newsType()
				).stream()
				.map(newsEntity -> new News(
						newsEntity.getId(),
						newsEntity.getNewsType(),
						newsEntity.getTitle(),
						newsEntity.getContent(),
						newsEntity.getViewCount()
				)).collect(Collectors.toList());
		long total = newsQuery.count();
		return new PageImpl<>(content, PageRequest.of(query.page(), query.size()), total);
	}
}
