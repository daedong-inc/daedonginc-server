package com.daedonginc.api.admin.dto;

/**
 * @author domo
 * Created on 2023/04/13
 */
public record LoginAdminRequestDto(
		String name,
		String password
) {
}
