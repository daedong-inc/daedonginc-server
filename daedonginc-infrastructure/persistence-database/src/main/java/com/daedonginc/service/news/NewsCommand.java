package com.daedonginc.service.news;

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
@Transactional
public class NewsCommand {
	private final NewsRepository newsRepository;

	public NewsCommand(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	public NewsEntity save(final NewsType newsType, final String title, final String content) {
		NewsEntity newsEntity = NewsEntity.newInstance(newsType, title, content);
		return newsRepository.save(newsEntity);
	}

	public void deleteById(final Long newsId) {
		NewsEntity newsEntity = newsRepository.findById(newsId)
				.orElseThrow(() -> new NotFoundNewsException(newsId));
		newsRepository.deleteById(newsEntity.getId());
	}

	public void update(Long newsId, NewsType newsType, String title, String content) {
		NewsEntity newsEntity = newsRepository.findById(newsId)
				.orElseThrow(() -> new NotFoundNewsException(newsId));
		newsEntity.update(newsType, title, content);
	}
}
