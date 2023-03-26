package com.daedonginc.repository.companyhistory;

import java.util.Optional;

import com.daedonginc.entity.companyhistory.CompanyHistoryEntity;

/**
 * @author domo
 * Created on 2023/03/23
 */
public interface CompanyHistoryRepository {
	CompanyHistoryEntity save(CompanyHistoryEntity companyHistoryEntity);

	Optional<CompanyHistoryEntity> findById(Long companyHistoryId);

	void deleteById(Long companyHistoryId);
}
