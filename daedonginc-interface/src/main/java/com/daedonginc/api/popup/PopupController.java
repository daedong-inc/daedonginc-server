package com.daedonginc.api.popup;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daedonginc.aop.AdminLoginCheck;
import com.daedonginc.api.popup.dto.CreatePopupRequestDto;
import com.daedonginc.api.popup.dto.PopupResponseDto;
import com.daedonginc.api.popup.mapper.PopupMapper;
import com.daedonginc.popup.domain.Popup;
import com.daedonginc.popup.usecase.CommandCreatePopupUseCase;
import com.daedonginc.popup.usecase.CommandDeletePopupUseCase;
import com.daedonginc.popup.usecase.CommandUpdatePopupUseCase;
import com.daedonginc.popup.usecase.QueryPopupActiveAllUseCase;
import com.daedonginc.popup.usecase.QueryPopupAllUseCase;
import com.daedonginc.util.DateTimeUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author domo
 * Created on 2023/04/02
 */
@RestController
@RequestMapping("/api/v1/popup")
@Tag(name = "popup", description = "팝업")
public class PopupController {
	private final QueryPopupAllUseCase queryPopupAllUseCase;
	private final QueryPopupActiveAllUseCase queryPopupActiveAllUseCase;
	private final CommandCreatePopupUseCase commandCreatePopupUseCase;
	private final CommandUpdatePopupUseCase commandUpdatePopupUseCase;
	private final CommandDeletePopupUseCase commandDeletePopupUseCase;

	public PopupController(
			final QueryPopupAllUseCase queryPopupAllUseCase,
			final QueryPopupActiveAllUseCase queryPopupActiveAllUseCase,
			final CommandCreatePopupUseCase commandCreatePopupUseCase,
			final CommandUpdatePopupUseCase commandUpdatePopupUseCase,
			final CommandDeletePopupUseCase commandDeletePopupUseCase
	) {
		this.queryPopupAllUseCase = queryPopupAllUseCase;
		this.queryPopupActiveAllUseCase = queryPopupActiveAllUseCase;
		this.commandCreatePopupUseCase = commandCreatePopupUseCase;
		this.commandUpdatePopupUseCase = commandUpdatePopupUseCase;
		this.commandDeletePopupUseCase = commandDeletePopupUseCase;
	}

	@AdminLoginCheck
	@GetMapping
	@Operation(summary = "팝업 목록", description = "팝업 목록을 조회합니다.")
	public List<PopupResponseDto> popupAll() {
		return queryPopupAllUseCase.query().stream()
				.map(PopupMapper::toResponseDto)
				.toList();
	}

	@GetMapping("/active")
	@Operation(summary = "활성화된 팝업 목록", description = "활성화된 팝업 목록을 조회합니다.")
	public List<Popup> popupActiveAll() {
		return queryPopupActiveAllUseCase.query();
	}

	@AdminLoginCheck
	@PostMapping
	@Operation(summary = "팝업 생성", description = "팝업을 생성합니다.")
	public PopupResponseDto createPopup(
			@RequestBody @Validated final CreatePopupRequestDto dto
	) {
		Popup popup = commandCreatePopupUseCase.command(new CommandCreatePopupUseCase.Command(
				dto.name(),
				dto.imageUrl(),
				dto.clickUrl(),
				dto.sort(),
				DateTimeUtil.getTodayFirstTime(dto.startDate()),
				DateTimeUtil.getTodayLastTime(dto.endDate())
		));

		return PopupMapper.toResponseDto(popup);
	}

	@AdminLoginCheck
	@PutMapping("{id}")
	@Operation(summary = "팝업 수정", description = "팝업을 수정합니다.")
	public void updatePopup(
			@Parameter(description = "팝업 ID", required = true) @PathVariable final Long id,
			@RequestBody @Validated final CreatePopupRequestDto dto
	) {
		commandUpdatePopupUseCase.command(new CommandUpdatePopupUseCase.Command(
				id,
				dto.name(),
				dto.imageUrl(),
				dto.clickUrl(),
				dto.sort(),
				DateTimeUtil.getTodayFirstTime(dto.startDate()),
				DateTimeUtil.getTodayLastTime(dto.endDate())
		));
	}

	@AdminLoginCheck
	@DeleteMapping("{id}")
	@Operation(summary = "팝업 삭제", description = "팝업을 삭제합니다.")
	public void deletePopup(
			@Parameter(description = "팝업 ID", required = true) @PathVariable final Long id
	) {
		commandDeletePopupUseCase.command(new CommandDeletePopupUseCase.Command(id));
	}
}
