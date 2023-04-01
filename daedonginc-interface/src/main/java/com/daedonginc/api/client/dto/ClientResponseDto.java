package com.daedonginc.api.client.dto;

/**
 * @author domo
 * Created on 2023/03/31
 */
public record ClientResponseDto(
		long id,
		String name,
		String imageUrl,
		String clickUrl,
		int sort,
		String createdAt,
		String lastModifiedAt
) {
}
