package com.daedonginc.news.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.news.usecase.CommandDeleteNewsUseCase;
import com.daedonginc.service.news.NewsCommand;

/**
 * @author domo
 * Created on 2023/04/12
 */
@Service
@Transactional
public class CommandDeleteNews implements CommandDeleteNewsUseCase {
	private final NewsCommand newsCommand;

	public CommandDeleteNews(NewsCommand newsCommand) {
		this.newsCommand = newsCommand;
	}

	@Override
	public void command(final Command command) {
		newsCommand.deleteById(command.newsId());
	}
}
