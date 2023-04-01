package com.daedonginc.client.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.client.usecase.CommandUpdateClientUseCase;
import com.daedonginc.service.client.ClientCommand;

/**
 * @author domo
 * Created on 2023/03/30
 */
@Service
@Transactional
public class CommandUpdateClient implements CommandUpdateClientUseCase {
	private final ClientCommand clientCommand;

	public CommandUpdateClient(final ClientCommand clientCommand) {
		this.clientCommand = clientCommand;
	}

	@Override
	public void command(final Command command) {
		clientCommand.update(command.id(), command.name(), command.imageUrl(), command.clickUrl(), command.sort());
	}
}
