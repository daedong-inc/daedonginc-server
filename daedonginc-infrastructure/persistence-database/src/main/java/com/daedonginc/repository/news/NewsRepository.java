package com.daedonginc.repository.news;

import java.util.Optional;

import com.daedonginc.entity.news.NewsEntity;

/**
 * @author domo
 * Created on 2023/03/27
 */
public interface NewsRepository {
	NewsEntity save(NewsEntity newsEntity);

	Optional<NewsEntity> findById(Long newsId);

	void deleteById(Long newsId);
}
