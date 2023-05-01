package com.daedonginc.companyhistory.usecase;

/**
 * @author domo
 * Created on 2023/03/30
 */
public interface CommandUpdateComponyHistoryUseCase {
	void command(final Command command);

	record Command(
			long id,
			int historyYear,
			String content,
			int sort
	) {
	}
}
