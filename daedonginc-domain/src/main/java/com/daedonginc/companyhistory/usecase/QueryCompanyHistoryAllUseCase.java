package com.daedonginc.companyhistory.usecase;

import java.util.List;

import com.daedonginc.companyhistory.domain.CompanyHistory;

/**
 * @author domo
 * Created on 2023/03/29
 */
public interface QueryCompanyHistoryAllUseCase {
	List<CompanyHistory> query();
}
