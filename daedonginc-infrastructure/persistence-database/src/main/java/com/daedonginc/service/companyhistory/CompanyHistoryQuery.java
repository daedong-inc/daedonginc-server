package com.daedonginc.service.companyhistory;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.companyhistory.CompanyHistoryEntity;
import com.daedonginc.repository.companyhistory.CompanyHistoryRepository;
import com.daedonginc.service.companyhistory.exception.NotFoundCompanyHistoryException;

/**
 * @author domo
 * Created on 2023/03/23
 */
@Service
@Transactional(readOnly = true)
public class CompanyHistoryQuery {
	private final CompanyHistoryRepository companyHistoryRepository;

	public CompanyHistoryQuery(final CompanyHistoryRepository companyHistoryRepository) {
		this.companyHistoryRepository = companyHistoryRepository;
	}

	public CompanyHistoryEntity findById(final Long companyHistoryId) {
		return companyHistoryRepository.findById(companyHistoryId)
				.orElseThrow(() -> new NotFoundCompanyHistoryException(companyHistoryId));
	}

	public List<CompanyHistoryEntity> findAll(final Sort sort) {
		return companyHistoryRepository.findAll(sort);
	}
}
