package com.daedonginc.repository.client;

import java.util.Optional;

import com.daedonginc.entity.client.ClientEntity;

/**
 * @author domo
 * Created on 2023/03/27
 */
public interface ClientRepository {

	ClientEntity save(ClientEntity clientEntity);

	Optional<ClientEntity> findById(Long clientId);

	void deleteById(Long clientId);
}
