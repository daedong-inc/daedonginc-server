package com.daedonginc.companyhistory.usecase;

import com.daedonginc.companyhistory.domain.CompanyHistory;

/**
 * @author domo
 * Created on 2023/03/30
 */
public interface CommandCreateComponyHistoryUseCase {
	CompanyHistory command(final Command command);

	record Command(
			int historyYear,
			String content,
			int sort
	) {
	}
}
