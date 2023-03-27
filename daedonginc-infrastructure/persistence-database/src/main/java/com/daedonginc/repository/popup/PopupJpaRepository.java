package com.daedonginc.repository.popup;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daedonginc.entity.popup.PopupEntity;

/**
 * @author domo
 * Created on 2023/03/23
 */
public interface PopupJpaRepository extends JpaRepository<PopupEntity, Long>, PopupRepository {
}
