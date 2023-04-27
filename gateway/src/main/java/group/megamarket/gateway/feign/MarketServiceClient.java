package group.megamarket.gateway.feign;


import group.megamarket.gateway.dto.market.OrderRequest;
import group.megamarket.gateway.dto.market.OrderResponse;
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

@FeignClient(value = "marketService", url = "http://localhost:8081/")
public interface MarketServiceClient {

    @PutMapping("/carts")
    OrderRequest addProduct(@RequestBody OrderRequest orderRequest);

    @GetMapping("/users")
    OrderResponse getOrder(@PathVariable Long userId);

    @PostMapping("/users")
    OrderResponse pay(@PathVariable Long userId);

    @DeleteMapping("/users")
    void clear(@PathVariable Long userId);
}
