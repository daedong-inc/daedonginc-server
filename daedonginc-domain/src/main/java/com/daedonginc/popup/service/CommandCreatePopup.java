package com.daedonginc.popup.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.popup.PopupEntity;
import com.daedonginc.popup.domain.Popup;
import com.daedonginc.popup.excepion.StartDateLaterThanEndDateException;
import com.daedonginc.popup.usecase.CommandCreatePopupUseCase;
import com.daedonginc.service.popup.PopupCommand;

/**
 * @author domo
 * Created on 2023/04/02
 */
@Service
@Transactional
public class CommandCreatePopup implements CommandCreatePopupUseCase {
	private final PopupCommand popupCommand;

	public CommandCreatePopup(final PopupCommand popupCommand) {
		this.popupCommand = popupCommand;
	}

	@Override
	public Popup command(final Command command) {
		if (command.startDate().isAfter(command.endDate())) {
			throw new StartDateLaterThanEndDateException(command.startDate(), command.endDate());
		}

		PopupEntity popupEntity = popupCommand.save(
				command.name(),
				command.imageUrl(),
				command.clickUrl(),
				command.sort(),
				command.startDate(),
				command.endDate()
		);
		return new Popup(
				popupEntity.getId(),
				popupEntity.getName(),
				popupEntity.getImageUrl(),
				popupEntity.getClickUrl(),
				popupEntity.getSort(),
				popupEntity.getStartDate(),
				popupEntity.getEndDate()
		);
	}
}
