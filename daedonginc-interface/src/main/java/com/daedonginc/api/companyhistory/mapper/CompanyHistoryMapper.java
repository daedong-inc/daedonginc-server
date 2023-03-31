package com.daedonginc.api.companyhistory.mapper;

import com.daedonginc.api.companyhistory.dto.CompanyHistoryResponseDto;
import com.daedonginc.companyhistory.domain.CompanyHistory;

/**
 * @author domo
 * Created on 2023/03/29
 */
public class CompanyHistoryMapper {
	public static CompanyHistoryResponseDto toResponseDto(final CompanyHistory companyHistory) {
		return new CompanyHistoryResponseDto(
				companyHistory.id(),
				companyHistory.historyYear(),
				companyHistory.content(),
				companyHistory.sort(),
				companyHistory.createdAt().toString(),
				companyHistory.lastModifiedAt().toString()
		);
	}
}
