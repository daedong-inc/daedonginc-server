package com.daedonginc.repository.popup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.daedonginc.entity.popup.PopupEntity;

/**
 * @author domo
 * Created on 2023/03/23
 */
public interface PopupRepository {
	PopupEntity save(PopupEntity popupEntity);

	Optional<PopupEntity> findById(Long popupId);

	void deleteById(Long popupId);

	List<PopupEntity> findAll();

	List<PopupEntity> findAllActivePopup(LocalDateTime now, Sort sort);

}
