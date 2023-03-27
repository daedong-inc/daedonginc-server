package com.daedonginc.repository.client;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daedonginc.entity.client.ClientEntity;

/**
 * @author domo
 * Created on 2023/03/27
 */
public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long>, ClientRepository {
}
