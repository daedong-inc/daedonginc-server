package com.daedonginc.api.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.daedonginc.api.news.dto.CreateNewsRequestDto;
import com.daedonginc.api.news.dto.NewsResponseDto;
import com.daedonginc.api.news.mapper.NewsMapper;
import com.daedonginc.model.news.NewsType;
import com.daedonginc.news.domain.News;
import com.daedonginc.news.usecase.CommandCreateNewsUserCase;
import com.daedonginc.news.usecase.CommandDeleteNewsUseCase;
import com.daedonginc.news.usecase.CommandUpdateNewsUseCase;
import com.daedonginc.news.usecase.QueryNewsAllByNewsTypeUseCase;
import com.daedonginc.news.usecase.QueryNewsByIdAndNewsTypeUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * @author domo
 * Created on 2023/04/11
 */
@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
	private final QueryNewsAllByNewsTypeUseCase queryNewsAllByNewsTypeUseCase;
	private final QueryNewsByIdAndNewsTypeUseCase queryNewsByIdAndNewsTypeUseCase;
	private final CommandCreateNewsUserCase commandCreateNewsUserCase;
	private final CommandUpdateNewsUseCase commandUpdateNewsUseCase;
	private final CommandDeleteNewsUseCase commandDeleteNewsUseCase;

	public NewsController(
			final QueryNewsAllByNewsTypeUseCase queryNewsAllByNewsTypeUseCase,
			final QueryNewsByIdAndNewsTypeUseCase queryNewsByIdAndNewsTypeUseCase,
			final CommandCreateNewsUserCase commandCreateNewsUserCase,
			final CommandUpdateNewsUseCase commandUpdateNewsUseCase,
			final CommandDeleteNewsUseCase commandDeleteNewsUseCase
	) {
		this.queryNewsAllByNewsTypeUseCase = queryNewsAllByNewsTypeUseCase;
		this.queryNewsByIdAndNewsTypeUseCase = queryNewsByIdAndNewsTypeUseCase;
		this.commandCreateNewsUserCase = commandCreateNewsUserCase;
		this.commandUpdateNewsUseCase = commandUpdateNewsUseCase;
		this.commandDeleteNewsUseCase = commandDeleteNewsUseCase;
	}

	@GetMapping("/{newsType}")
	@Operation(summary = "뉴스 리스트 조회", description = "뉴스 리스트 조회")
	public Page<NewsResponseDto> newsAllByNewsType(
			@Parameter(description = "뉴스 타입", required = true) @PathVariable final NewsType newsType,
			Pageable pageable
	) {
		return queryNewsAllByNewsTypeUseCase.query(
				new QueryNewsAllByNewsTypeUseCase.Query(pageable.getPageNumber(), pageable.getPageSize(), newsType)
		).map(NewsMapper::toResponseDto);
	}

	@GetMapping("/{newsType}/{id}")
	@Operation(summary = "뉴스 상세 조회", description = "뉴스 상세 조회")
	public NewsResponseDto newsByIdAndNewsType(
			@Parameter(description = "뉴스 타입", required = true) @PathVariable final NewsType newsType,
			@Parameter(description = "뉴스 아이디", required = true) @PathVariable final Long id
	) {
		return NewsMapper.toResponseDto(
				queryNewsByIdAndNewsTypeUseCase.query(
						new QueryNewsByIdAndNewsTypeUseCase.Query(id, newsType)
				)
		);
	}

	@AdminLoginCheck
	@PostMapping
	@Operation(summary = "뉴스 생성", description = "뉴스 생성")
	public NewsResponseDto createNews(
			@RequestBody @Validated final CreateNewsRequestDto dto
	) {
		News news = commandCreateNewsUserCase.command(
				new CommandCreateNewsUserCase.Command(
						dto.newsType(),
						dto.title(),
						dto.content()
				)
		);
		return NewsMapper.toResponseDto(news);
	}

	@AdminLoginCheck
	@PutMapping("/{id}")
	@Operation(summary = "뉴스 수정", description = "뉴스 수정")
	public void updateNews(
			@Parameter(description = "뉴스 아이디", required = true) @PathVariable final Long id,
			@RequestBody @Validated final CreateNewsRequestDto dto
	) {
		commandUpdateNewsUseCase.command(
				new CommandUpdateNewsUseCase.Command(
						id,
						dto.newsType(),
						dto.title(),
						dto.content()
				)
		);
	}

	@AdminLoginCheck
	@DeleteMapping("/{id}")
	@Operation(summary = "뉴스 삭제", description = "뉴스 삭제")
	public void deleteNews(
			@Parameter(description = "뉴스 아이디", required = true) @PathVariable final Long id
	) {
		commandDeleteNewsUseCase.command(new CommandDeleteNewsUseCase.Command(id));
	}
}
