package com.daedonginc.repository.companyhistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daedonginc.entity.companyhistory.CompanyHistoryEntity;

/**
 * @author domo
 * Created on 2023/03/23
 */
public interface CompanyHistoryJpaRepository
		extends JpaRepository<CompanyHistoryEntity, Long> {
}
