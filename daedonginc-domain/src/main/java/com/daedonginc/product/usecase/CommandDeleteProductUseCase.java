package com.daedonginc.product.usecase;

/**
 * @author domo
 * Created on 2023/04/09
 */
public interface CommandDeleteProductUseCase {
	void command(Command command);

	record Command(long productId) {
	}
}
