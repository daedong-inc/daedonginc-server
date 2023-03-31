package com.daedonginc.api.companyhistory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daedonginc.api.companyhistory.dto.CompanyHistoryResponseDto;
import com.daedonginc.api.companyhistory.dto.CreateCompanyHistoryRequestDto;
import com.daedonginc.api.companyhistory.dto.UpdateCompanyHistoryRequestDto;
import com.daedonginc.api.companyhistory.mapper.CompanyHistoryMapper;
import com.daedonginc.companyhistory.domain.CompanyHistory;
import com.daedonginc.companyhistory.usecase.CommandCreateComponyHistoryUseCase;
import com.daedonginc.companyhistory.usecase.CommandDeleteComponyHistoryUseCase;
import com.daedonginc.companyhistory.usecase.CommandUpdateComponyHistoryUseCase;
import com.daedonginc.companyhistory.usecase.QueryCompanyHistoryAllUseCase;

/**
 * @author domo
 * Created on 2023/03/29
 */
@RestController
@RequestMapping("/api/v1/company-history")
public class CompanyHistoryRestController {
	private final QueryCompanyHistoryAllUseCase queryCompanyHistoryByAllUseCase;
	private final CommandCreateComponyHistoryUseCase commandCreateComponyHistoryUseCase;
	private final CommandUpdateComponyHistoryUseCase commandUpdateComponyHistoryUseCase;
	private final CommandDeleteComponyHistoryUseCase commandDeleteComponyHistoryUseCase;

	public CompanyHistoryRestController(
			final QueryCompanyHistoryAllUseCase queryCompanyHistoryByAllUseCase,
			final CommandCreateComponyHistoryUseCase commandCreateComponyHistoryUseCase,
			final CommandUpdateComponyHistoryUseCase commandUpdateComponyHistoryUseCase,
			final CommandDeleteComponyHistoryUseCase commandDeleteComponyHistoryUseCase
	) {
		this.queryCompanyHistoryByAllUseCase = queryCompanyHistoryByAllUseCase;
		this.commandCreateComponyHistoryUseCase = commandCreateComponyHistoryUseCase;
		this.commandUpdateComponyHistoryUseCase = commandUpdateComponyHistoryUseCase;
		this.commandDeleteComponyHistoryUseCase = commandDeleteComponyHistoryUseCase;
	}

	@GetMapping
	public List<CompanyHistoryResponseDto> companyHistory() {
		return queryCompanyHistoryByAllUseCase.query().stream()
				.map(CompanyHistoryMapper::toResponseDto)
				.collect(Collectors.toList());
	}

	@PostMapping
	public CompanyHistoryResponseDto createCompanyHistory(
			@RequestBody @Validated final CreateCompanyHistoryRequestDto dto
	) {
		CompanyHistory companyHistory = commandCreateComponyHistoryUseCase.command(
				new CommandCreateComponyHistoryUseCase.Command(
						dto.historyYear(),
						dto.content(),
						dto.sort()
				));
		return CompanyHistoryMapper.toResponseDto(companyHistory);
	}

	@PutMapping("{id}")
	public void updateCompanyHistory(
			@PathVariable final Long id,
			@RequestBody @Validated final UpdateCompanyHistoryRequestDto dto
	) {
		commandUpdateComponyHistoryUseCase.command(
				new CommandUpdateComponyHistoryUseCase.Command(id, dto.historyYear(), dto.content(), dto.sort())
		);
	}

	@DeleteMapping("{id}")
	public void deleteCompanyHistory(@PathVariable final Long id) {
		commandDeleteComponyHistoryUseCase.command(
				new CommandDeleteComponyHistoryUseCase.Command(id)
		);
	}
}
