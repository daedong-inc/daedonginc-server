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

import com.daedonginc.aop.AdminLoginCheck;
import com.daedonginc.api.companyhistory.dto.CompanyHistoryResponseDto;
import com.daedonginc.api.companyhistory.dto.CreateCompanyHistoryRequestDto;
import com.daedonginc.api.companyhistory.dto.UpdateCompanyHistoryRequestDto;
import com.daedonginc.api.companyhistory.mapper.CompanyHistoryMapper;
import com.daedonginc.companyhistory.domain.CompanyHistory;
import com.daedonginc.companyhistory.usecase.CommandCreateComponyHistoryUseCase;
import com.daedonginc.companyhistory.usecase.CommandDeleteComponyHistoryUseCase;
import com.daedonginc.companyhistory.usecase.CommandUpdateComponyHistoryUseCase;
import com.daedonginc.companyhistory.usecase.QueryCompanyHistoryAllUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author domo
 * Created on 2023/03/29
 */
@RestController
@RequestMapping("/api/v1/company-history")
@Tag(name = "CompanyHistory", description = "회사연혁")
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
	@Operation(summary = "회사연혁 조회", description = "회사 연혁을 조회합니다.")
	public List<CompanyHistoryResponseDto> companyHistory() {
		return queryCompanyHistoryByAllUseCase.query().stream()
				.map(CompanyHistoryMapper::toResponseDto)
				.collect(Collectors.toList());
	}

	@AdminLoginCheck
	@PostMapping
	@Operation(summary = "회사연혁 생성", description = "회사 연혁을 생성합니다.")
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

	@AdminLoginCheck
	@PutMapping("{id}")
	@Operation(summary = "회사연혁 수정", description = "회사 연혁을 수정합니다.")
	public void updateCompanyHistory(
			@Parameter(description = "회사 연혁 ID", required = true) @PathVariable final Long id,
			@RequestBody @Validated final UpdateCompanyHistoryRequestDto dto
	) {
		commandUpdateComponyHistoryUseCase.command(
				new CommandUpdateComponyHistoryUseCase.Command(id, dto.historyYear(), dto.content(), dto.sort())
		);
	}

	@AdminLoginCheck
	@DeleteMapping("{id}")
	@Operation(summary = "회사연혁 삭제", description = "회사 연혁을 삭제합니다.")
	public void deleteCompanyHistory(
			@Parameter(description = "회사 연혁 ID", required = true) @PathVariable final Long id
	) {
		commandDeleteComponyHistoryUseCase.command(
				new CommandDeleteComponyHistoryUseCase.Command(id)
		);
	}
}
