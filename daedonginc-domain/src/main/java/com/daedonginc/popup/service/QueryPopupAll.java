package com.daedonginc.popup.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.popup.domain.Popup;
import com.daedonginc.popup.usecase.QueryPopupAllUseCase;
import com.daedonginc.service.popup.PopupQuery;

/**
 * @author domo
 * Created on 2023/04/02
 */
@Service
@Transactional(readOnly = true)
public class QueryPopupAll implements QueryPopupAllUseCase {
	private final PopupQuery popupQuery;

	public QueryPopupAll(final PopupQuery popupQuery) {
		this.popupQuery = popupQuery;
	}

	@Override
	public List<Popup> query() {
		return popupQuery.findAll().stream()
				.map(popupEntity -> new Popup(
						popupEntity.getId(),
						popupEntity.getName(),
						popupEntity.getImageUrl(),
						popupEntity.getClickUrl(),
						popupEntity.getSort(),
						popupEntity.getStartDate(),
						popupEntity.getEndDate()
				)).collect(Collectors.toList());
	}
}
