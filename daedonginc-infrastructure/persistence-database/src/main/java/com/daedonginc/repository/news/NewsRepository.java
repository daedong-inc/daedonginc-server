package com.daedonginc.repository.news;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.daedonginc.entity.news.NewsEntity;
import com.daedonginc.model.news.NewsType;

/**
 * @author domo
 * Created on 2023/03/27
 */
public interface NewsRepository {
	NewsEntity save(NewsEntity newsEntity);

	Optional<NewsEntity> findById(Long newsId);

	void deleteById(Long newsId);

	Page<NewsEntity> findAllByNewsType(Pageable pageable, NewsType newsType);

	long count();

	Optional<NewsEntity> findByIdAndNewsType(Long newsId, NewsType newsType);
}
