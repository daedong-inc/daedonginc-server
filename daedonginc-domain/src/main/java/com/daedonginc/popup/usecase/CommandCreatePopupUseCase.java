package com.daedonginc.popup.usecase;

import java.time.LocalDateTime;

import com.daedonginc.popup.domain.Popup;

/**
 * @author domo
 * Created on 2023/04/02
 */
public interface CommandCreatePopupUseCase {
	Popup command(Command command);

	record Command(
			String name,
			String imageUrl,
			String clickUrl,
			int sort,
			LocalDateTime startDate,
			LocalDateTime endDate
	) {
	}
}
