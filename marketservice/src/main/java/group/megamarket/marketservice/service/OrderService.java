package group.megamarket.marketservice.service;

import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.OrderProduct;

/**
 * Интерфейс сервиса для управления корзиной пользователя
 *
 * @author Eldar
 */
public interface OrderService {
    /**
     * Метод добавляет продукт в корзину покупателя
     *
     * @param orderProduct добавляемый продукт
     * @param userId       id покупателя
     * @return Order корзина пользователя
     */
    Order addProduct(OrderProduct orderProduct, Long userId);

    /**
     * Метод получает корзину покупателя
     *
     * @param userId id покупателя
     * @return Order корзина пользователя
     */
    Order getOrder(Long userId);

    /**
     * Метод подтверждения оплаты корзины
     *
     * @param userId id покупателя
     * @return Order оплаченная корзина
     */
    Order pay(Long userId);

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
