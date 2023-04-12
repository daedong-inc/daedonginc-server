package com.daedonginc.news.usecase;

import com.daedonginc.model.news.NewsType;
import com.daedonginc.news.domain.News;

/**
 * @author domo
 * Created on 2023/04/11
 */
public interface CommandCreateNewsUserCase {
	News command(Command command);

	record Command(
			NewsType newsType,
			String title,
			String content
	) {
	}
}
