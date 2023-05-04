package group.megamarket.marketservice.service;

import group.megamarket.marketservice.dto.OrderRequestDto;
import group.megamarket.marketservice.dto.OrderResponseDto;

/**
 * Интерфейс сервиса для управления корзиной пользователя
 *
 * @author Eldar
 */
public interface OrderService {
    /**
     * Метод добавляет продукт в корзину покупателя
     *
     * @param orderRequestDto добавляемый продукт
     * @param userId          id покупателя
     * @return Order корзина пользователя
     */
    OrderResponseDto addProduct(OrderRequestDto orderRequestDto, Long userId);

    /**
     * Метод получает корзину покупателя
     *
     * @param userId id покупателя
     * @return Order корзина пользователя
     */
    OrderResponseDto getOrder(Long userId);

    /**
     * Метод подтверждения оплаты корзины
     *
     * @param userId id покупателя
     * @return Order оплаченная корзина
     */
    OrderResponseDto pay(Long userId);

    /**
     * Метод удаления продукта из корзины
     *
     * @param userId    id покупателя
     * @param productId id продукта
     */
    void deleteProduct(Long userId, Long productId);

    /**
     * Метод очистки корзины покупателя
     *
     * @param userId id покупателя
     */
    void clear(Long userId);
}
