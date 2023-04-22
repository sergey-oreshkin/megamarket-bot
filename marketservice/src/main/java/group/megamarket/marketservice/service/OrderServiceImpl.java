package group.megamarket.marketservice.service;

import group.megamarket.marketservice.dto.OrderRequest;
import group.megamarket.marketservice.dto.OrderResponse;
import group.megamarket.marketservice.dto.ProductResponse;
import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.Status;
import group.megamarket.marketservice.exception.ForbiddenException;
import group.megamarket.marketservice.mapper.OrderMapper;
import group.megamarket.marketservice.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    private final OrderMapper mapper;

    @Override
    public OrderResponse addProduct(OrderRequest orderRequest) {
        log.info("addProduct is called");
        ProductResponse productResponse
                = restTemplate.getForObject(
                "http://localhost:8000/storageservice" + orderRequest.getProductId(),
                ProductResponse.class
        );

        if(productResponse.getCount() < orderRequest.getQuantity()){
            log.error("Количество добавленного товара превышает количество товара на складе");
            throw new ForbiddenException("Количество добавленного товара превышает количество товара на складе");
        }

        Order order = mapper.toOrder(orderRequest);

        order.setOrderDate(LocalDate.now());
        order.setStatus(Status.AWAITING_PAYMENT);

        Order savedOrder = repository.save(order);
        log.info("Product added in order successfully");

       return mapper.toOrderResponse(savedOrder);
    }

    @Override
    public Order getOrder(Long userId) {
        return null;
    }

    @Override
    public Order pay(Long userId) {
        return null;
    }

    @Override
    public void deleteByProductId(Long userId, Long productId) {

    }

    @Override
    public void clear(Long userId) {

    }
}
