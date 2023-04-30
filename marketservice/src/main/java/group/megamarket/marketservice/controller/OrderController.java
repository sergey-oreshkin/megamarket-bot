package group.megamarket.marketservice.controller;


import group.megamarket.marketservice.dto.OrderRequestDto;
import group.megamarket.marketservice.dto.OrderResponseDto;
import group.megamarket.marketservice.mapper.OrderMapper;
import group.megamarket.marketservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class OrderController {
    private final OrderService service;
    private final OrderMapper orderMapper;

    @PutMapping
    public OrderResponseDto addProduct(@RequestBody OrderRequestDto orderRequestDto) {
        var orderProduct = orderMapper.toOrderProduct(orderRequestDto);

        var order = service.addProduct(orderProduct, orderRequestDto.getUserId());

        return orderMapper.toOrderResponse(order);
    }

    @GetMapping("/users/{userId}")
    public OrderResponseDto getOrder(@PathVariable Long userId) {
        var order = service.getOrder(userId);

        return orderMapper.toOrderResponse(order);
    }

    @PostMapping("/users/{userId}")
    public OrderResponseDto pay(@PathVariable Long userId) {
        var order = service.pay(userId);

        return orderMapper.toOrderResponse(order);
    }

    @DeleteMapping("/users/{userId}/products/{productId}")
    public void deleteProduct(@PathVariable Long userId, @PathVariable Long productId) {
        service.deleteProduct(userId, productId);
    }

    @DeleteMapping("/users/{userId}")
    public void clear(@PathVariable Long userId) {
        service.clear(userId);
    }

}
