package com.daedonginc.api.news.dto;

import com.daedonginc.model.news.NewsType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author domo
 * Created on 2023/04/11
 */
public record UpdateNewsRequestDto(
		@NotNull(message = "타입은 필수입니다.")
		NewsType newsType,
		@NotBlank(message = "제목은 필수입니다.")
		String title,
		@NotBlank(message = "내용은 필수입니다.")
		String contentS
) {
}
