package com.daedonginc.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daedonginc.entity.news.NewsEntity;

/**
 * @author domo
 * Created on 2023/03/27
 */
public interface NewsJpaRepository extends JpaRepository<NewsEntity, Long>, NewsRepository {
}
