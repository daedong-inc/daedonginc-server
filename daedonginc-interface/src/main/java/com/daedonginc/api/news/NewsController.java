package com.daedonginc.api.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daedonginc.api.news.dto.CreateNewsRequestDto;
import com.daedonginc.api.news.dto.NewsResponseDto;
import com.daedonginc.api.news.mapper.NewsMapper;
import com.daedonginc.model.news.NewsType;
import com.daedonginc.news.usecase.CommandCreateNewsUserCase;
import com.daedonginc.news.usecase.QueryNewsAllByNewsTypeUseCase;
import com.daedonginc.news.usecase.QueryNewsByIdAndNewsTypeUseCase;

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

	public NewsController(
			final QueryNewsAllByNewsTypeUseCase queryNewsAllByNewsTypeUseCase,
			final QueryNewsByIdAndNewsTypeUseCase queryNewsByIdAndNewsTypeUseCase,
			final CommandCreateNewsUserCase commandCreateNewsUserCase
	) {
		this.queryNewsAllByNewsTypeUseCase = queryNewsAllByNewsTypeUseCase;
		this.queryNewsByIdAndNewsTypeUseCase = queryNewsByIdAndNewsTypeUseCase;
		this.commandCreateNewsUserCase = commandCreateNewsUserCase;
	}

	@GetMapping("/{newsType}")
	public Page<NewsResponseDto> newsAllByNewsType(
			@PathVariable final NewsType newsType,
			Pageable pageable
	) {
		return queryNewsAllByNewsTypeUseCase.query(
				new QueryNewsAllByNewsTypeUseCase.Query(pageable.getPageNumber(), pageable.getPageSize(), newsType)
		).map(NewsMapper::toResponseDto);
	}

	@GetMapping("/{newsType}/{id}")
	public NewsResponseDto newsByIdAndNewsType(
			@PathVariable final NewsType newsType,
			@PathVariable final Long id
	) {
		return NewsMapper.toResponseDto(
				queryNewsByIdAndNewsTypeUseCase.query(
						new QueryNewsByIdAndNewsTypeUseCase.Query(id, newsType)
				)
		);
	}

	@PostMapping
	public void createNews(
			@RequestBody @Validated final CreateNewsRequestDto dto
	) {
		commandCreateNewsUserCase.command(
				new CommandCreateNewsUserCase.Command(
						dto.newsType(),
						dto.title(),
						dto.content()
				)
		);
	}
}
