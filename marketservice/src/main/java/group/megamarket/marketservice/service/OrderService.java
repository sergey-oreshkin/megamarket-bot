package group.megamarket.marketservice.service;

import group.megamarket.marketservice.dto.OrderRequest;
import group.megamarket.marketservice.dto.OrderResponse;

public interface OrderService {
    OrderResponse addProduct(OrderRequest orderRequest);

    OrderResponse getOrder(Long userId);

    OrderResponse pay(Long userId);

    void deleteProduct(Long userId, Long productId);

    void clear(Long userId);
}
