package com.daedonginc.companyhistory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.companyhistory.usecase.CommandUpdateComponyHistoryUseCase;
import com.daedonginc.service.companyhistory.CompanyHistoryCommand;

/**
 * @author domo
 * Created on 2023/03/30
 */
@Service
@Transactional
public class CommandUpdateComponyHistory implements CommandUpdateComponyHistoryUseCase {
	private final CompanyHistoryCommand companyHistoryCommand;

	public CommandUpdateComponyHistory(final CompanyHistoryCommand companyHistoryCommand) {
		this.companyHistoryCommand = companyHistoryCommand;
	}

	@Override
	public void command(final Command command) {
		companyHistoryCommand.update(command.id(), command.historyYear(), command.content(), command.sort());
	}
}
