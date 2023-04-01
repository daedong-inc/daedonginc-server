package com.daedonginc.repository.popup;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.daedonginc.entity.popup.PopupEntity;

/**
 * @author domo
 * Created on 2023/03/23
 */
public interface PopupJpaRepository extends JpaRepository<PopupEntity, Long>, PopupRepository {

	@Query("SELECT p FROM PopupEntity p WHERE p.startDate <= :now AND p.endDate >= :now order by p.sort")
	List<PopupEntity> findAllActivePopup(LocalDateTime now, Sort sort);
}
