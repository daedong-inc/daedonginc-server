package com.daedonginc.repository.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.daedonginc.entity.news.NewsEntity;
import com.daedonginc.model.news.NewsType;

/**
 * @author domo
 * Created on 2023/03/27
 */
public interface NewsJpaRepository extends JpaRepository<NewsEntity, Long>, NewsRepository {
	Page<NewsEntity> findAllByNewsType(Pageable pageable, NewsType newsType);

	long count();
}
