package com.daedonginc.admin.usecase;

/**
 * @author domo
 * Created on 2023/04/13
 */
public interface QueryAdminLoginUseCase {
	void query(Query query);

	record Query(
			String name,
			String password
	) {
	}
}
