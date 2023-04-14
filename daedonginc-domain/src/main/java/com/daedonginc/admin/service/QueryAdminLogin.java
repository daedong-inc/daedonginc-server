package com.daedonginc.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.admin.usecase.QueryAdminLoginUseCase;
import com.daedonginc.service.admin.AdminQuery;

/**
 * @author domo
 * Created on 2023/04/13
 */
@Service
@Transactional
public class QueryAdminLogin implements QueryAdminLoginUseCase {
	private final AdminQuery adminQuery;

	public QueryAdminLogin(AdminQuery adminQuery) {
		this.adminQuery = adminQuery;
	}

	@Override
	public void query(Query query) {
		adminQuery.login(query.name(), query.password());
	}
}
