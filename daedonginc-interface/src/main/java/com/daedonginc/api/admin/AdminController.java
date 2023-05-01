package com.daedonginc.api.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daedonginc.admin.usecase.QueryAdminLoginUseCase;
import com.daedonginc.api.admin.dto.LoginAdminRequestDto;
import com.daedonginc.util.SHA256Util;
import com.daedonginc.util.SessionUtil;

import jakarta.servlet.http.HttpSession;

/**
 * @author domo
 * Created on 2023/04/13
 */
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
	private final QueryAdminLoginUseCase queryAdminLoginUseCase;

	public AdminController(final QueryAdminLoginUseCase queryAdminLoginUseCase) {
		this.queryAdminLoginUseCase = queryAdminLoginUseCase;
	}

	@PostMapping("/login")
	public void login(
			HttpSession session,
			@RequestBody final LoginAdminRequestDto dto
	) {
		queryAdminLoginUseCase.query(
				new QueryAdminLoginUseCase.Query(dto.name(), SHA256Util.encrypt(dto.password()))
		);

		SessionUtil.setLoginAdminId(session, dto.name());
	}

	@PostMapping("/logout")
	public void logout(HttpSession session) {
		SessionUtil.removeLoginAdminId(session);
	}

	@GetMapping("/check")
	public boolean check(HttpSession session) {
		return SessionUtil.getLoginAdminId(session) != null;
	}
}
