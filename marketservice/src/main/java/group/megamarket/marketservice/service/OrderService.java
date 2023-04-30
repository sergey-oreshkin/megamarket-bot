package group.megamarket.marketservice.service;

import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.OrderProduct;

public interface OrderService {
    Order addProduct(OrderProduct orderProduct, Long userId);

    Order getOrder(Long userId);

    Order pay(Long userId);

    void deleteProduct(Long userId, Long productId);

    void clear(Long userId);
}
