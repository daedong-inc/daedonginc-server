package com.daedonginc.api.popup.mapper;

import com.daedonginc.api.popup.dto.PopupResponseDto;
import com.daedonginc.popup.domain.Popup;

/**
 * @author domo
 * Created on 2023/04/02
 */
public class PopupMapper {
	public static PopupResponseDto toResponseDto(final Popup popup) {
		return new PopupResponseDto(
				popup.id(),
				popup.name(),
				popup.imageUrl(),
				popup.clickUrl(),
				popup.sort(),
				popup.startDate(),
				popup.endDate()
		);
	}
}
