package com.daedonginc.companyhistory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.companyhistory.usecase.CommandDeleteComponyHistoryUseCase;
import com.daedonginc.service.companyhistory.CompanyHistoryCommand;

/**
 * @author domo
 * Created on 2023/03/30
 */
@Service
@Transactional
public class CommandDeleteComponyHistory implements CommandDeleteComponyHistoryUseCase {
	private final CompanyHistoryCommand companyHistoryCommand;

	public CommandDeleteComponyHistory(final CompanyHistoryCommand companyHistoryCommand) {
		this.companyHistoryCommand = companyHistoryCommand;
	}

	@Override
	public void command(final Command command) {
		companyHistoryCommand.deleteById(command.companyHistoryId());
	}
}
