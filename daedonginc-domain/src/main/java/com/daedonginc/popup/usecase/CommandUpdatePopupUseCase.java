package com.daedonginc.popup.usecase;

import java.time.LocalDateTime;

/**
 * @author domo
 * Created on 2023/04/02
 */
public interface CommandUpdatePopupUseCase {
	void command(Command command);

	record Command(
			long id,
			String name,
			String imageUrl,
			String clickUrl,
			int sort,
			LocalDateTime startDate,
			LocalDateTime endDate
	) {
	}
}
