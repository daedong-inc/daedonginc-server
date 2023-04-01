package com.daedonginc.popup.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.popup.excepion.StartDateLaterThanEndDateException;
import com.daedonginc.popup.usecase.CommandUpdatePopupUseCase;
import com.daedonginc.service.popup.PopupCommand;

/**
 * @author domo
 * Created on 2023/04/02
 */
@Service
@Transactional
public class CommandUpdatePopup implements CommandUpdatePopupUseCase {
	private final PopupCommand popupCommand;

	public CommandUpdatePopup(final PopupCommand popupCommand) {
		this.popupCommand = popupCommand;
	}

	@Override
	public void command(final Command command) {
		if (command.startDate().isAfter(command.endDate())) {
			throw new StartDateLaterThanEndDateException(command.startDate(), command.endDate());
		}
		popupCommand.update(
				command.id(),
				command.name(),
				command.imageUrl(),
				command.clickUrl(),
				command.sort(),
				command.startDate(),
				command.endDate()
		);
	}
}
