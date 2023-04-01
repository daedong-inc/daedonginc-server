package com.daedonginc.service.popup;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
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
@Transactional(readOnly = true)
public class PopupQuery {
	private final PopupRepository popupRepository;

	public PopupQuery(PopupRepository popupRepository) {
		this.popupRepository = popupRepository;
	}

	public PopupEntity findById(final Long popupId) {
		return popupRepository.findById(popupId)
				.orElseThrow(() -> new NotFoundPopupException(popupId));
	}

	public List<PopupEntity> findAll() {
		return popupRepository.findAll();
	}

	public List<PopupEntity> findAllActivePopup() {
		return popupRepository.findAllActivePopup(
				LocalDateTime.now(),
				Sort.by(Sort.Direction.ASC, "sort")
		);
	}
}
