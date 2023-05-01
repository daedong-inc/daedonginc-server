package com.daedonginc.util;

import jakarta.servlet.http.HttpSession;

/**
 * @author domo
 * Created on 2023/03/28
 */
public class SessionUtil {
	private static final String LOGIN_ADMIN_ID = "LOGIN_ADMIN_ID";

	private SessionUtil() {
	}

	public static String getLoginAdminId(HttpSession httpSession) {
		return (String)httpSession.getAttribute(LOGIN_ADMIN_ID);
	}

	public static void setLoginAdminId(HttpSession httpSession, String loginAdminId) {
		httpSession.setAttribute(LOGIN_ADMIN_ID, loginAdminId);
	}

	public static void removeLoginAdminId(HttpSession httpSession) {
		httpSession.invalidate();
	}
}
