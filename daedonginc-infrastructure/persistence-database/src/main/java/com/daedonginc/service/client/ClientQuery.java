package com.daedonginc.service.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.client.ClientEntity;
import com.daedonginc.repository.client.ClientRepository;
import com.daedonginc.service.client.exception.NotFoundClientException;

/**
 * @author domo
 * Created on 2023/03/27
 */
@Service
@Transactional(readOnly = true)
public class ClientQuery {
	private final ClientRepository clientRepository;

	public ClientQuery(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public ClientEntity findById(final Long clientId) {
		return clientRepository.findById(clientId)
				.orElseThrow(() -> new NotFoundClientException(clientId));
	}

	public Page<ClientEntity> findAll(final Pageable pageable) {
		return clientRepository.findAll(pageable);
	}
}
