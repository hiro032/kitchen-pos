package hiro.kitchenpos.global.error;

public enum ErrorMessages {

	NOT_FOUND_PRODUCT("식별자에 해당하는 Product 를 찾을 수 없습니다."),

	MENU_NAME_EMPTY("메뉴 이름을 찾을 수 없습니다.");

	/**
	 *
	 * ~~~
	 * ~~~
	 * ~~~
	 *
	 */

	private final String MESSAGE;

	ErrorMessages(final String MESSAGE) {
		this.MESSAGE = MESSAGE;
	}
}
