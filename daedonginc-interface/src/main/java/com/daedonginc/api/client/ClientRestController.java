package com.daedonginc.api.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daedonginc.api.client.dto.ClientResponseDto;
import com.daedonginc.api.client.dto.CreateClientRequestDto;
import com.daedonginc.api.client.dto.UpdateClientRequestDto;
import com.daedonginc.api.client.mapper.ClientMapper;
import com.daedonginc.client.domain.Client;
import com.daedonginc.client.usecase.CommandCreateClientUseCase;
import com.daedonginc.client.usecase.CommandDeleteClientUseCase;
import com.daedonginc.client.usecase.CommandUpdateClientUseCase;
import com.daedonginc.client.usecase.QueryClientAllUseCase;

/**
 * @author domo
 * Created on 2023/03/31
 */
@RestController
@RequestMapping("/api/v1/client")
public class ClientRestController {
	private final QueryClientAllUseCase queryClientAllUseCase;
	private final CommandCreateClientUseCase commandCreateClientUseCase;
	private final CommandUpdateClientUseCase commandUpdateClientUseCase;
	private final CommandDeleteClientUseCase commandDeleteClientUseCase;

	public ClientRestController(
			final QueryClientAllUseCase queryClientAllUseCase,
			final CommandCreateClientUseCase commandCreateClientUseCase,
			final CommandUpdateClientUseCase commandUpdateClientUseCase,
			final CommandDeleteClientUseCase commandDeleteClientUseCase
	) {
		this.queryClientAllUseCase = queryClientAllUseCase;
		this.commandCreateClientUseCase = commandCreateClientUseCase;
		this.commandUpdateClientUseCase = commandUpdateClientUseCase;
		this.commandDeleteClientUseCase = commandDeleteClientUseCase;
	}

	@GetMapping
	public Page<ClientResponseDto> clientAll(Pageable pageable) {
		return queryClientAllUseCase.query(
				new QueryClientAllUseCase.Query(pageable.getPageNumber(), pageable.getPageSize())
		).map(ClientMapper::toResponseDto);
	}

	@PostMapping
	public ClientResponseDto createClient(
			@RequestBody @Validated final CreateClientRequestDto dto
	) {
		Client client = commandCreateClientUseCase.command(
				new CommandCreateClientUseCase.Command(
						dto.name(),
						dto.imageUrl(),
						dto.clickUrl(),
						dto.sort()
				));
		return ClientMapper.toResponseDto(client);
	}

	@PutMapping("{id}")
	public void updateClient(
			@PathVariable final Long id,
			@RequestBody @Validated final UpdateClientRequestDto dto
	) {
		commandUpdateClientUseCase.command(
				new CommandUpdateClientUseCase.Command(
						id,
						dto.name(),
						dto.imageUrl(),
						dto.clickUrl(),
						dto.sort()
				));
	}

	@DeleteMapping("{id}")
	public void deleteClient(@PathVariable final Long id) {
		commandDeleteClientUseCase.command(new CommandDeleteClientUseCase.Command(id));
	}
}
