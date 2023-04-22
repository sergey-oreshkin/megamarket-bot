package group.megamarket.marketservice.service;

import group.megamarket.marketservice.dto.OrderRequest;
import group.megamarket.marketservice.dto.OrderResponse;
import group.megamarket.marketservice.entity.Order;

public interface OrderService {
    OrderResponse addProduct(OrderRequest orderRequest);

    Order getOrder(Long userId);

    Order pay(Long userId);

    void deleteByProductId(Long userId, Long productId);

    void clear(Long userId);
}
