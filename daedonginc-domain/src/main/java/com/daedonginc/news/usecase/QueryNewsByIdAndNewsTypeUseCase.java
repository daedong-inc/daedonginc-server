package com.daedonginc.news.usecase;

import com.daedonginc.model.news.NewsType;
import com.daedonginc.news.domain.News;

/**
 * @author domo
 * Created on 2023/04/11
 */
public interface QueryNewsByIdAndNewsTypeUseCase {
	News query(Query query);

	record Query(
			long id,
			NewsType newsType
	) {
	}
}
