package com.daedonginc.service.client;

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
@Transactional
public class ClientCommand {
	private final ClientRepository clientRepository;

	public ClientCommand(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public ClientEntity save(final String name, final String imageUrl, final String clickUrl) {
		ClientEntity clientEntity = ClientEntity.newInstance(name, imageUrl, clickUrl);
		return clientRepository.save(clientEntity);
	}

	public void deleteById(final Long clientId) {
		ClientEntity clientEntity = clientRepository.findById(clientId)
				.orElseThrow(() -> new NotFoundClientException(clientId));
		clientRepository.deleteById(clientEntity.getId());
	}
}
