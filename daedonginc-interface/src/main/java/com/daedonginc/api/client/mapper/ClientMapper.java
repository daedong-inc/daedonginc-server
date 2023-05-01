package com.daedonginc.api.client.mapper;

import com.daedonginc.api.client.dto.ClientResponseDto;
import com.daedonginc.client.domain.Client;

/**
 * @author domo
 * Created on 2023/03/31
 */
public class ClientMapper {
	public static ClientResponseDto toResponseDto(final Client client) {
		return new ClientResponseDto(
				client.id(),
				client.name(),
				client.imageUrl(),
				client.clickUrl(),
				client.sort(),
				client.createdAt().toString(),
				client.lastModifiedAt().toString()
		);
	}
}
