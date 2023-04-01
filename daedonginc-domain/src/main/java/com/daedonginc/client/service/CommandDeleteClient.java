package com.daedonginc.client.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.client.usecase.CommandDeleteClientUseCase;
import com.daedonginc.service.client.ClientCommand;

/**
 * @author domo
 * Created on 2023/03/30
 */
@Service
@Transactional
public class CommandDeleteClient implements CommandDeleteClientUseCase {
	private final ClientCommand clientCommand;

	public CommandDeleteClient(final ClientCommand clientCommand) {
		this.clientCommand = clientCommand;
	}

	@Override
	public void command(final Command command) {
		clientCommand.deleteById(command.clientId());
	}
}
