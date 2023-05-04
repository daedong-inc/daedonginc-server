package com.daedonginc.api.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author domo
 * Created on 2023/04/05
 */
@Schema(name = "CreateProductRequestDto", description = "상품 생성 요청")
public record CreateProductRequestDto(
		@Schema(description = "카테고리ID", example = "1")
		@NotNull(message = "카테고리ID는 필수입니다.")
		Long categoryId,
		@NotEmpty(message = "상품명은 필수입니다.")
		@Schema(description = "상품명", example = "Powder Pot B")
		String name,
		@NotNull(message = "용량은 필수입니다.")
		@Schema(description = "용량", example = "5")
		Integer volume,
		@NotEmpty(message = "사이즈는 필수입니다.")
		@Schema(description = "사이즈", example = "Diameter: 37mm / Height: 37mm")
		String size,
		@NotEmpty(message = "부품재질은 필수입니다.")
		@Schema(description = "부품 재질", example = "Cap: ABS / Rod: POM / Bottle: PETG / Applicator: TPE Flocked Tip")
		String partMaterial,
		@NotEmpty(message = "상품설명은 필수입니다.")
		@Schema(description = "상품 설명", example = "용기의 투명한 두께감을 강조할 수 있는 각진 사각 형상의 솔대형 용기방향성을 잡아주기 위한 스토퍼가 구비됨")
		String description,
		@NotEmpty(message = "이미지URL은 필수입니다.")
		@Schema(description = "이미지 URL", example = "https://daedong-storage.s3.ap-northeast-2.amazonaws.com/product/1/1.png")
		String imageUrl
) {
}
