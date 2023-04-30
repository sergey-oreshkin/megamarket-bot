package group.megamarket.marketservice.controller;


import group.megamarket.marketservice.dto.OrderRequestDto;
import group.megamarket.marketservice.dto.OrderResponseDto;
import group.megamarket.marketservice.mapper.OrderMapper;
import group.megamarket.marketservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Класс представляет собой контроллер для управления корзиной покупателя
 *
 * @author Eldar
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class OrderController {
    private final OrderService service;
    private final OrderMapper orderMapper;

    /**
     * Метод обрабатывает PUT-запрос на добавление продукта в корзину
     *
     * @param orderRequestDto объект получаемый при запросе
     * @return OrderResponseDto
     */
    @PutMapping
    public OrderResponseDto addProduct(@RequestBody OrderRequestDto orderRequestDto) {
        var orderProduct = orderMapper.toOrderProduct(orderRequestDto);

        var order = service.addProduct(orderProduct, orderRequestDto.getUserId());

        return orderMapper.toOrderResponse(order);
    }

    /**
     * Метод обрабатывает GET-запрос на получение корзины покупателя
     *
     * @param userId id покупателя
     * @return OrderResponseDto
     */
    @GetMapping("/users/{userId}")
    public OrderResponseDto getOrder(@PathVariable Long userId) {
        var order = service.getOrder(userId);

        return orderMapper.toOrderResponse(order);
    }

    /**
     * Метод обрабатывает POST-запрос на подтверждение оплаты
     *
     * @param userId id покупателя
     * @return OrderResponseDto
     */
    @PostMapping("/users/{userId}")
    public OrderResponseDto pay(@PathVariable Long userId) {
        var order = service.pay(userId);

        return orderMapper.toOrderResponse(order);
    }

    /**
     * Метод обрабатывает DELETE-запрос на удаление продукта из корзины
     *
     * @param userId    id покупателя
     * @param productId id продукта
     */
    @DeleteMapping("/users/{userId}/products/{productId}")
    public void deleteProduct(@PathVariable Long userId, @PathVariable Long productId) {
        service.deleteProduct(userId, productId);
    }

    /**
     * Метод обрабатывает DELETE-запрос на удаление корзины
     *
     * @param userId id покупателя
     */
    @DeleteMapping("/users/{userId}")
    public void clear(@PathVariable Long userId) {
        service.clear(userId);
    }

}
