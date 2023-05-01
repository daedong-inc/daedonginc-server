package com.daedonginc.client.domain;

import java.time.LocalDateTime;

/**
 * @author domo
 * Created on 2023/03/31
 */
public record Client(
		long id,
		String name,
		String imageUrl,
		String clickUrl,
		int sort,
		LocalDateTime createdAt,
		LocalDateTime lastModifiedAt
) {
}
