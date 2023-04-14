package com.daedonginc.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.repository.admin.AdminRepository;
import com.daedonginc.service.admin.exception.NotFoundAdminException;

/**
 * @author domo
 * Created on 2023/03/28
 */
@Service
@Transactional(readOnly = true)
public class AdminQuery {
	private final AdminRepository adminRepository;

	public AdminQuery(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	public void login(String name, String password) {
		adminRepository.findByNameAndPassword(name, password)
				.orElseThrow(() -> new NotFoundAdminException(name));
	}
}
