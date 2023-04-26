package com.daedonginc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.daedonginc.aop.exception.AdminUnauthorizedException;
import com.daedonginc.util.SessionUtil;

import jakarta.servlet.http.HttpSession;

/**
 * @author domo
 * Created on 2023/04/14
 */
@Aspect
@Component
public class AdminLoginCheckAspect {

	@Around("@annotation(AdminLoginCheck)")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest()
				.getSession();
		String adminId = SessionUtil.getLoginAdminId(session);

		if (adminId == null) {
			throw new AdminUnauthorizedException();
		}
		pjp.proceed();
	}
}
