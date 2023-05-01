package com.daedonginc.companyhistory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.companyhistory.domain.CompanyHistory;
import com.daedonginc.companyhistory.usecase.QueryCompanyHistoryAllUseCase;
import com.daedonginc.service.companyhistory.CompanyHistoryQuery;

/**
 * @author domo
 * Created on 2023/03/29
 */
@Service
@Transactional(readOnly = true)
public class QueryCompanyHistoryAll implements QueryCompanyHistoryAllUseCase {
	private final CompanyHistoryQuery companyHistoryQuery;

	public QueryCompanyHistoryAll(final CompanyHistoryQuery companyHistoryQuery) {
		this.companyHistoryQuery = companyHistoryQuery;
	}

	@Override
	public List<CompanyHistory> query() {
		return companyHistoryQuery.findAll(Sort.by(Sort.Direction.ASC, "sort")).stream()
				.map(companyHistoryEntity -> new CompanyHistory(
						companyHistoryEntity.getId(),
						companyHistoryEntity.getHistoryYear(),
						companyHistoryEntity.getContent(),
						companyHistoryEntity.getSort(),
						companyHistoryEntity.getCreatedAt(),
						companyHistoryEntity.getLastModifiedAt()
				)).collect(Collectors.toList());
	}
}
