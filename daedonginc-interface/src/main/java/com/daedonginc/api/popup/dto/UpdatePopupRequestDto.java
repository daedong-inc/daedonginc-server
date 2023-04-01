package com.daedonginc.api.popup.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author domo
 * Created on 2023/04/02
 */
public record UpdatePopupRequestDto(
		@NotEmpty(message = "팝업명은 필수입니다.")
		String name,
		@NotEmpty(message = "이미지URL은 필수입니다.")
		String imageUrl,
		@NotEmpty(message = "클릭URL은 필수입니다.")
		String clickUrl,
		@NotNull(message = "정렬순서는 필수입니다.")
		int sort,
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@NotNull(message = "시작일은 필수입니다.")
		LocalDate startDate,
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@NotNull(message = "종료일은 필수입니다.")
		LocalDate endDate
) {
}
