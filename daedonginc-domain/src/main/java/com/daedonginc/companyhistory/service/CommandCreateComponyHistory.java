package com.daedonginc.companyhistory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.companyhistory.domain.CompanyHistory;
import com.daedonginc.companyhistory.usecase.CommandCreateComponyHistoryUseCase;
import com.daedonginc.entity.companyhistory.CompanyHistoryEntity;
import com.daedonginc.service.companyhistory.CompanyHistoryCommand;

/**
 * @author domo
 * Created on 2023/03/30
 */
@Service
@Transactional
public class CommandCreateComponyHistory implements CommandCreateComponyHistoryUseCase {
	private final CompanyHistoryCommand companyHistoryCommand;

	public CommandCreateComponyHistory(final CompanyHistoryCommand companyHistoryCommand) {
		this.companyHistoryCommand = companyHistoryCommand;
	}

	@Override
	public CompanyHistory command(final Command command) {
		CompanyHistoryEntity companyHistoryEntity = companyHistoryCommand.save(command.historyYear(), command.content(),
				command.sort());
		return new CompanyHistory(
				companyHistoryEntity.getId(),
				companyHistoryEntity.getHistoryYear(),
				companyHistoryEntity.getContent(),
				companyHistoryEntity.getSort(),
				companyHistoryEntity.getCreatedAt(),
				companyHistoryEntity.getLastModifiedAt()
		);

	}
}
