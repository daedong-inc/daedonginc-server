package com.daedonginc.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daedonginc.product.usecase.CommandDeleteProductUseCase;
import com.daedonginc.service.product.ProductCommand;

/**
 * @author domo
 * Created on 2023/04/09
 */
@Service
@Transactional
public class CommandDeleteProduct implements CommandDeleteProductUseCase {
	private final ProductCommand productCommand;

	public CommandDeleteProduct(final ProductCommand productCommand) {
		this.productCommand = productCommand;
	}

	@Override
	public void command(final Command command) {
		productCommand.deleteById(command.productId());
	}
}
