package com.daedonginc.service.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.news.NewsEntity;
import com.daedonginc.model.news.NewsType;
import com.daedonginc.repository.news.NewsRepository;
import com.daedonginc.service.news.exception.NotFoundNewsException;

/**
 * @author domo
 * Created on 2023/03/27
 */
@Service
@Transactional(readOnly = true)
public class NewsQuery {
	private final NewsRepository newsRepository;

	public NewsQuery(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	public NewsEntity findById(final Long newsId) {
		return newsRepository.findById(newsId)
				.orElseThrow(() -> new NotFoundNewsException(newsId));
	}

	public Page<NewsEntity> findAllByNewsType(Pageable pageable, NewsType newsType) {
		return newsRepository.findAllByNewsType(pageable, newsType);
	}

	public long count() {
		return newsRepository.count();
	}

	public NewsEntity findByIdAndNewsType(final Long newsId, final NewsType newsType) {
		return newsRepository.findByIdAndNewsType(newsId, newsType)
				.orElseThrow(() -> new NotFoundNewsException(newsId));
	}
}
