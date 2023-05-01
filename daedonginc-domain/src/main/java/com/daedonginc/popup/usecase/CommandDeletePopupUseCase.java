package com.daedonginc.popup.usecase;

/**
 * @author domo
 * Created on 2023/04/02
 */
public interface CommandDeletePopupUseCase {
	void command(Command command);

	record Command(long id) {
	}
}
