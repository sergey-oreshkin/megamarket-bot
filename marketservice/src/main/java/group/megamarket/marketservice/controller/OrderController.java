package group.megamarket.marketservice.controller;


import group.megamarket.marketservice.dto.OrderRequestDto;
import group.megamarket.marketservice.dto.OrderResponseDto;
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

    /**
     * Метод обрабатывает PUT-запрос на добавление продукта в корзину
     *
     * @param orderRequestDto объект получаемый при запросе
     * @return OrderResponseDto
     */
    @PutMapping
    public OrderResponseDto addProduct(@RequestBody OrderRequestDto orderRequestDto) {
        return service.addProduct(orderRequestDto, orderRequestDto.getUserId());
    }

    /**
     * Метод обрабатывает GET-запрос на получение корзины покупателя
     *
     * @param userId id покупателя
     * @return OrderResponseDto
     */
    @GetMapping("/users/{userId}")
    public OrderResponseDto getOrder(@PathVariable Long userId) {
        return service.getOrder(userId);
    }

    /**
     * Метод обрабатывает POST-запрос на подтверждение оплаты
     *
     * @param userId id покупателя
     * @return OrderResponseDto
     */
    @PostMapping("/users/{userId}")
    public OrderResponseDto pay(@PathVariable Long userId) {
        return service.pay(userId);
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
