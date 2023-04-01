package com.daedonginc.popup.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.popup.usecase.CommandDeletePopupUseCase;
import com.daedonginc.service.popup.PopupCommand;

/**
 * @author domo
 * Created on 2023/04/02
 */
@Service
@Transactional
public class CommandDeletePopup implements CommandDeletePopupUseCase {
	private final PopupCommand popupCommand;

	public CommandDeletePopup(final PopupCommand popupCommand) {
		this.popupCommand = popupCommand;
	}

	@Override
	public void command(final Command command) {
		popupCommand.deleteById(command.id());
	}
}
