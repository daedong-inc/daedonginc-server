package com.daedonginc.service.popup;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.popup.PopupEntity;
import com.daedonginc.repository.popup.PopupRepository;
import com.daedonginc.service.popup.exception.NotFoundPopupException;

/**
 * @author domo
 * Created on 2023/03/27
 */
@Service
@Transactional
public class PopupCommand {
	private final PopupRepository popupRepository;

	public PopupCommand(PopupRepository popupRepository) {
		this.popupRepository = popupRepository;
	}

	public PopupEntity save(final String name, final String imageUrl, final String clickUrl) {
		PopupEntity popupEntity = PopupEntity.newInstance(name, imageUrl, clickUrl);
		return popupRepository.save(popupEntity);
	}

	public void deleteById(final Long popupId) {
		PopupEntity popupEntity = popupRepository.findById(popupId)
				.orElseThrow(() -> new NotFoundPopupException(popupId));
		popupRepository.deleteById(popupEntity.getId());
	}
}
