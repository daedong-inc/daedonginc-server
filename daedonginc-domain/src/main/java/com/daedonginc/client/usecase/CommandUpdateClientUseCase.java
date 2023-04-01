package com.daedonginc.client.usecase;

/**
 * @author domo
 * Created on 2023/03/31
 */
public interface CommandUpdateClientUseCase {
	void command(final Command command);

	record Command(
			long id,
			String name,
			String imageUrl,
			String clickUrl,
			int sort
	) {
	}
}
