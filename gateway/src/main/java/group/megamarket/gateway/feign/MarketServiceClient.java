package group.megamarket.gateway.feign;


import group.megamarket.gateway.dto.market.OrderRequestDto;
import group.megamarket.gateway.dto.market.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Клиент для доступа к marketService
 */
@FeignClient(value = "marketService", url = "${market-service.url}")
public interface MarketServiceClient {

    @PutMapping("/carts")
    OrderRequestDto addProduct(@RequestBody OrderRequestDto orderRequestDto);

    @GetMapping("/carts/users")
    OrderResponseDto getOrder(@PathVariable Long userId);

    @PostMapping("/carts/users")
    OrderResponseDto pay(@PathVariable Long userId);

    @DeleteMapping("/carts/users")
    void clear(@PathVariable Long userId);
}
