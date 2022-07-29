package hiro.kitchenpos.delivery_orders.application;

import hiro.kitchenpos.delivery_orders.application.dtos.OrderLineItemCommand;
import hiro.kitchenpos.menu.domain.MenuRepository;
import hiro.kitchenpos.menu.domain.exception.MenuNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeliveryOrderServiceValidator {

	private final MenuRepository menuRepository;

	// 주문한 menu 의 식별자를 통해 존재하는 메뉴인지 확인.
	public void validateMenuIds(final List<OrderLineItemCommand> orderLineItemCommands) {
		List<UUID> menuIds = orderLineItemCommands.stream()
				.map(OrderLineItemCommand::getMenuId)
				.collect(Collectors.toList());

		long count = menuIds.stream()
				.filter(menuRepository::existsById)
				.count();

		if (menuIds.size() != count) {
			throw new MenuNotFoundException();
		}
	}
}
