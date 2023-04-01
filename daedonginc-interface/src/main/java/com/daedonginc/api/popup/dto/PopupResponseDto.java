package com.daedonginc.api.popup.dto;

import java.time.LocalDateTime;

/**
 * @author domo
 * Created on 2023/04/02
 */
public record PopupResponseDto(
		long id,
		String name,
		String imageUrl,
		String clickUrl,
		int sort,
		LocalDateTime startDate,
		LocalDateTime endDate
) {
}
