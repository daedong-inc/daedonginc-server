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

	public CompanyHistoryCommand(CompanyHistoryRepository companyHistoryRepository) {
		this.companyHistoryRepository = companyHistoryRepository;
	}

	public CompanyHistoryEntity save(final int year, final String content) {
		CompanyHistoryEntity companyHistoryEntity = CompanyHistoryEntity.newInstance(year, content);
		return companyHistoryRepository.save(companyHistoryEntity);
	}

	public void deleteById(final Long companyHistoryId) {
		CompanyHistoryEntity companyHistoryEntity = companyHistoryRepository.findById(companyHistoryId)
				.orElseThrow(() -> new NotFoundCompanyHistoryException(companyHistoryId));
		companyHistoryRepository.deleteById(companyHistoryEntity.getId());
	}
}
