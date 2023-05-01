package com.daedonginc.news.usecase;

/**
 * @author domo
 * Created on 2023/04/12
 */
public interface CommandDeleteNewsUseCase {
	void command(Command command);

	record Command(
			Long newsId
	) {
	}
}
