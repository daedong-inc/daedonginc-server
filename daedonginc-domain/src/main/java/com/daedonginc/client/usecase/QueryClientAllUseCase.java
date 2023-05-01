package com.daedonginc.client.usecase;

import org.springframework.data.domain.Page;

import com.daedonginc.client.domain.Client;

/**
 * @author domo
 * Created on 2023/03/31
 */
public interface QueryClientAllUseCase {
	Page<Client> query(final Query query);

	record Query(
			int page,
			int size
	) {
	}
}
