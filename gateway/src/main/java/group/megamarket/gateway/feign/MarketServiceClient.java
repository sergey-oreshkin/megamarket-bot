package group.megamarket.gateway.feign;


import group.megamarket.gateway.dto.market.OrderRequestDto;
import group.megamarket.gateway.dto.market.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Клиент для доступа к marketService
 */
@FeignClient(value = "marketService", url = "${market-service.url}")
public interface MarketServiceClient {

    @PutMapping("/carts")
    OrderRequestDto addProduct(@RequestBody OrderRequestDto orderRequestDto);

    @GetMapping("/carts/users/{userId}")
    OrderResponseDto getOrder(@PathVariable Long userId);

    @PostMapping("/carts/users/{userId}")
    OrderResponseDto pay(@PathVariable Long userId);

    @DeleteMapping("/carts/users/{userId}")
    void clear(@PathVariable Long userId);

    @DeleteMapping("/users/{userId}/products/{productId}")
    void deleteProduct(@PathVariable Long userId, @PathVariable Long productId);
}
