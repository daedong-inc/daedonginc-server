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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

/**
 * @author domo
 * Created on 2023/04/13
 */
@RestController
@RequestMapping("api/v1/admin")
@Tag(name = "admin", description = "관리자")
public class AdminController {
	private final QueryAdminLoginUseCase queryAdminLoginUseCase;

	public AdminController(final QueryAdminLoginUseCase queryAdminLoginUseCase) {
		this.queryAdminLoginUseCase = queryAdminLoginUseCase;
	}

	@PostMapping("/login")
	@Operation(summary = "관리자 로그인", description = "name, password를 받아 로그인을 진행합니다.")
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
	@Operation(summary = "관리자 로그아웃", description = "로그아웃을 진행합니다.")
	public void logout(HttpSession session) {
		SessionUtil.removeLoginAdminId(session);
	}

	@GetMapping("/check")
	@Operation(summary = "관리자 로그인 체크", description = "관리자 로그인 여부를 확인합니다.")
	public boolean check(HttpSession session) {
		return SessionUtil.getLoginAdminId(session) != null;
	}
}
