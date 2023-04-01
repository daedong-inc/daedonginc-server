package com.daedonginc.api.client.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author domo
 * Created on 2023/03/30
 */
public record CreateClientRequestDto(
		@NotNull(message = "고객사명은 필수입니다.")
		String name,
		@NotEmpty(message = "이미지URL은 필수입니다.")
		String imageUrl,
		@NotEmpty(message = "클릭URL은 필수입니다.")
		String clickUrl,
		@NotNull(message = "정렬순서는 필수입니다.")
		int sort
) {
}
