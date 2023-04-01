package com.daedonginc.client.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.client.domain.Client;
import com.daedonginc.client.usecase.CommandCreateClientUseCase;
import com.daedonginc.entity.client.ClientEntity;
import com.daedonginc.service.client.ClientCommand;

/**
 * @author domo
 * Created on 2023/03/30
 */
@Service
@Transactional
public class CommandCreateClient implements CommandCreateClientUseCase {
	private final ClientCommand clientCommand;

	public CommandCreateClient(final ClientCommand clientCommand) {
		this.clientCommand = clientCommand;
	}

	@Override
	public Client command(final Command command) {
		ClientEntity clientEntity = clientCommand.save(command.name(), command.imageUrl(), command.clickUrl(),
				command.sort());
		return new Client(
				clientEntity.getId(),
				clientEntity.getName(),
				clientEntity.getImageUrl(),
				clientEntity.getClickUrl(),
				clientEntity.getSort(),
				clientEntity.getCreatedAt(),
				clientEntity.getLastModifiedAt()
		);

	}
}
