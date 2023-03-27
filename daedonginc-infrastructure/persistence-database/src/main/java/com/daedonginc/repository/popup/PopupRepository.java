package com.daedonginc.repository.popup;

import java.util.Optional;

import com.daedonginc.entity.popup.PopupEntity;

/**
 * @author domo
 * Created on 2023/03/23
 */
public interface PopupRepository {
	PopupEntity save(PopupEntity popupEntity);

	Optional<PopupEntity> findById(Long popupId);

	void deleteById(Long popupId);
}
