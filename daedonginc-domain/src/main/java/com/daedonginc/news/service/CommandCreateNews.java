package com.daedonginc.news.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.entity.news.NewsEntity;
import com.daedonginc.news.domain.News;
import com.daedonginc.news.usecase.CommandCreateNewsUserCase;
import com.daedonginc.service.news.NewsCommand;

/**
 * @author domo
 * Created on 2023/04/11
 */
@Service
@Transactional
public class CommandCreateNews implements CommandCreateNewsUserCase {
	private final NewsCommand newsCommand;

	public CommandCreateNews(final NewsCommand newsCommand) {
		this.newsCommand = newsCommand;
	}

	@Override
	public News command(final Command command) {
		NewsEntity newsEntity = newsCommand.save(command.newsType(), command.title(), command.content());
		return new News(
				newsEntity.getId(),
				newsEntity.getNewsType(),
				newsEntity.getTitle(),
				newsEntity.getContent(),
				newsEntity.getViewCount()
		);
	}
}
