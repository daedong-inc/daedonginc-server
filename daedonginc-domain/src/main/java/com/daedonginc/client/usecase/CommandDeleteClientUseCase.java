package com.daedonginc.client.usecase;

/**
 * @author domo
 * Created on 2023/03/30
 */
public interface CommandDeleteClientUseCase {
	void command(final Command command);

	record Command(
			long clientId
	) {
	}
}
