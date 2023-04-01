package com.daedonginc.client.usecase;

import com.daedonginc.client.domain.Client;

/**
 * @author domo
 * Created on 2023/03/31
 */
public interface CommandCreateClientUseCase {
	Client command(final Command command);

	record Command(
			String name,
			String imageUrl,
			String clickUrl,
			int sort
	) {
	}
}
