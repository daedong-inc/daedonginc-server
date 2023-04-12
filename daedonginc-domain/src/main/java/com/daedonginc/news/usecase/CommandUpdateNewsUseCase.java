package com.daedonginc.news.usecase;

import com.daedonginc.model.news.NewsType;

/**
 * @author domo
 * Created on 2023/04/12
 */
public interface CommandUpdateNewsUseCase {
	void command(Command command);

	record Command(
			Long newsId,
			NewsType newsType,
			String title,
			String content
	) {
	}
}
