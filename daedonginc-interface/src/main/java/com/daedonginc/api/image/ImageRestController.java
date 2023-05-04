package com.daedonginc.api.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.daedonginc.api.image.dto.ImageResponseDto;
import com.daedonginc.model.image.ImageCategory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author domo
 * Created on 2023/04/19
 */
@RestController
@RequestMapping("/api/v1/image")
@Tag(name = "Image", description = "이미지")
public class ImageRestController {
	private final AmazonS3 amazonS3;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public ImageRestController(final AmazonS3 amazonS3) {
		this.amazonS3 = amazonS3;
	}

	@PostMapping(value = "/{imageCategory}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "이미지 업로드", description = "이미지를 업로드합니다.")
	public ImageResponseDto uploadImage(
			@Parameter(description = "이미지 카테고리", required = true) @PathVariable ImageCategory imageCategory,
			@RequestParam MultipartFile image
	) throws IOException {
		final var imageFile = convert(image)
				.orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

		String uuid = UUID.randomUUID().toString();

		return new ImageResponseDto(
				upload(imageFile, imageCategory, uuid),
				imageCategory,
				uuid
		);
	}

	private String upload(File uploadFile, ImageCategory imageCategory, String uuid) {
		final var fileName = String.format("images/%s/%s3", imageCategory.name(), uuid);

		amazonS3.putObject(
				new PutObjectRequest(bucket, fileName, uploadFile)
						.withCannedAcl(CannedAccessControlList.PublicRead)
		);
		removeNewFile(uploadFile);
		return amazonS3.getUrl(bucket, fileName).toString();
	}

	private void removeNewFile(File targetFile) {
		targetFile.delete();
	}

	private Optional<File> convert(MultipartFile file) throws IOException {
		File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
		if (convertedFile.createNewFile()) {
			try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
				fos.write(file.getBytes());
			}
			return Optional.of(convertedFile);
		}

		return Optional.empty();
	}
}
