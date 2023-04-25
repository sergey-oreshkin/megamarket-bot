package group.megamarket.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Клиент для доступа к marketService
 */

@FeignClient(value = "marketService", url = "http://localhost:8081/")
public interface MarketServiceClient {

}
