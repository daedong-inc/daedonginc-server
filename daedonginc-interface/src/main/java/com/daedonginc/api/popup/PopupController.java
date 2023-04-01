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

/**
 * @author domo
 * Created on 2023/04/02
 */
@RestController
@RequestMapping("/api/v1/popup")
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

	@GetMapping
	public List<PopupResponseDto> popupAll() {
		return queryPopupAllUseCase.query().stream()
				.map(PopupMapper::toResponseDto)
				.toList();
	}

	@GetMapping("/active")
	public List<Popup> popupActiveAll() {
		return queryPopupActiveAllUseCase.query();
	}

	@PostMapping
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

	@PutMapping("{id}")
	public void updatePopup(
			@PathVariable final Long id,
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

	@DeleteMapping("{id}")
	public void deletePopup(@PathVariable final Long id) {
		commandDeletePopupUseCase.command(new CommandDeletePopupUseCase.Command(id));
	}
}
