package com.daedonginc.client.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.client.domain.Client;
import com.daedonginc.client.usecase.QueryClientAllUseCase;
import com.daedonginc.service.client.ClientQuery;

/**
 * @author domo
 * Created on 2023/03/31
 */
@Service
@Transactional(readOnly = true)
public class QueryClientAll implements QueryClientAllUseCase {
	private final ClientQuery clientQuery;

	public QueryClientAll(final ClientQuery clientQuery) {
		this.clientQuery = clientQuery;
	}

	@Override
	public Page<Client> query(Query query) {
		return clientQuery.findAll(PageRequest.of(
						query.page(),
						query.size(),
						Sort.Direction.ASC,
						"sort"
				))
				.map(clientEntity -> new Client(
						clientEntity.getId(),
						clientEntity.getName(),
						clientEntity.getImageUrl(),
						clientEntity.getClickUrl(),
						clientEntity.getSort(),
						clientEntity.getCreatedAt(),
						clientEntity.getLastModifiedAt()
				));
	}
}
