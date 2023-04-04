package com.daedonginc.category.domain;

import java.util.List;

/**
 * @author domo
 * Created on 2023/04/03
 */
public record Category(
		long id,
		String name,
		String level,
		List<Category> children
) {
}
