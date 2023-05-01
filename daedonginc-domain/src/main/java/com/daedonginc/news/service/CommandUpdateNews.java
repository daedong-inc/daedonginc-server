package com.daedonginc.news.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.news.usecase.CommandUpdateNewsUseCase;
import com.daedonginc.service.news.NewsCommand;

/**
 * @author domo
 * Created on 2023/04/12
 */
@Service
@Transactional
public class CommandUpdateNews implements CommandUpdateNewsUseCase {
	private final NewsCommand newsCommand;

	public CommandUpdateNews(
			final NewsCommand newsCommand
	) {
		this.newsCommand = newsCommand;
	}

	@Override
	public void command(final Command command) {
		newsCommand.update(command.newsId(), command.newsType(), command.title(), command.content());
	}
}
