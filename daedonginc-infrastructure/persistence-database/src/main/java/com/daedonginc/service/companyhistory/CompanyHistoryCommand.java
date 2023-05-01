package com.daedonginc.service.companyhistory;

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
@Transactional
public class CompanyHistoryCommand {
	private final CompanyHistoryRepository companyHistoryRepository;

	public CompanyHistoryCommand(final CompanyHistoryRepository companyHistoryRepository) {
		this.companyHistoryRepository = companyHistoryRepository;
	}

	public CompanyHistoryEntity save(final int year, final String content, final int order) {
		CompanyHistoryEntity companyHistoryEntity = CompanyHistoryEntity.of(year, content, order);
		return companyHistoryRepository.save(companyHistoryEntity);
	}

	public void update(final Long companyHistoryId, final int year, final String content, final int order) {
		CompanyHistoryEntity companyHistoryEntity = companyHistoryRepository.findById(companyHistoryId)
				.orElseThrow(() -> new NotFoundCompanyHistoryException(companyHistoryId));
		companyHistoryEntity.update(year, content, order);
		companyHistoryRepository.save(companyHistoryEntity);
	}

	public void deleteById(final Long companyHistoryId) {
		CompanyHistoryEntity companyHistoryEntity = companyHistoryRepository.findById(companyHistoryId)
				.orElseThrow(() -> new NotFoundCompanyHistoryException(companyHistoryId));
		companyHistoryRepository.deleteById(companyHistoryEntity.getId());
	}
}
