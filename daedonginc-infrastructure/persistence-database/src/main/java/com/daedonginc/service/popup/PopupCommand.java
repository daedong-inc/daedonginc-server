package com.daedonginc.service.popup;

import java.time.LocalDateTime;

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

	public PopupEntity save(
			final String name,
			final String imageUrl,
			final String clickUrl,
			final int sort,
			final LocalDateTime startDate,
			final LocalDateTime endDate
	) {
		PopupEntity popupEntity = PopupEntity.newInstance(name, imageUrl, clickUrl, sort, startDate, endDate);
		return popupRepository.save(popupEntity);
	}

	public void update(final Long id, final String name, final String imageUrl, final String clickUrl,
			final int sort, final LocalDateTime startDate, final LocalDateTime endDate) {
		PopupEntity popupEntity = popupRepository.findById(id)
				.orElseThrow(() -> new NotFoundPopupException(id));
		popupEntity.update(name, imageUrl, clickUrl, sort, startDate, endDate);
		popupRepository.save(popupEntity);
	}

	public void deleteById(final Long popupId) {
		PopupEntity popupEntity = popupRepository.findById(popupId)
				.orElseThrow(() -> new NotFoundPopupException(popupId));
		popupRepository.deleteById(popupEntity.getId());
	}
}
