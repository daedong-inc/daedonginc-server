package com.daedonginc.companyhistory.domain;

import java.time.LocalDateTime;

/**
 * @author domo
 * Created on 2023/03/29
 */
public record CompanyHistory(
		long id,
		int historyYear,
		String content,
		int sort,
		LocalDateTime createdAt,
		LocalDateTime lastModifiedAt
) {
}
