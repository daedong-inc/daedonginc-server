package com.daedonginc.api.companyhistory.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author domo
 * Created on 2023/03/30
 */
public record UpdateCompanyHistoryRequestDto(
		@NotNull(message = "연도는 필수입니다.")
		int historyYear,
		@NotEmpty(message = "내용은 필수입니다.")
		String content,
		@NotNull(message = "정렬순서는 필수입니다.")
		int sort
) {
}
