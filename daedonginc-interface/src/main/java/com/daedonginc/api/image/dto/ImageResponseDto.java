package com.daedonginc.api.image.dto;

import com.daedonginc.model.image.ImageCategory;

/**
 * @author domo
 * Created on 2023/04/26
 */
public record ImageResponseDto(
		String imageUrl,
		ImageCategory imageCategory,
		String uuid
) {
}
