package com.daedonginc.api.companyhistory.dto;

/**
 * @author domo
 * Created on 2023/03/29
 */
public record CompanyHistoryResponseDto(
		long id,
		int historyYear,
		String content,
		int sort,
		String createdAt,
		String lastModifiedAt
) {
}
