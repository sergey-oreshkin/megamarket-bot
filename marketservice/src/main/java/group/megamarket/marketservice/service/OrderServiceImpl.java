package group.megamarket.marketservice.service;

import group.megamarket.marketservice.dto.OrderRequest;
import group.megamarket.marketservice.dto.OrderResponse;
import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.entity.OrderProductPK;
import group.megamarket.marketservice.entity.Status;
import group.megamarket.marketservice.exception.NotFoundException;
import group.megamarket.marketservice.mapper.OrderMapper;
import group.megamarket.marketservice.repo.OrderProductRepository;
import group.megamarket.marketservice.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    private final OrderMapper mapper;

    @Override
    public OrderResponse addProduct(OrderRequest orderRequest) {
        log.info("addProduct is called");

        /*ProductDto productResponse = getProduct(orderRequest.getProductId());
        if(productResponse.getCount() < orderRequest.getQuantity()){
            log.error("Количество добавленного товара превышает количество товара на складе");
            throw new BadRequestException("Количество добавленного товара превышает количество товара на складе");
        }*/

        var order = orderRepository.findByUserIdAndStatus(orderRequest.getUserId(), Status.AWAITING_PAYMENT)
                                   .orElse(Order.builder()
                                                .orderProducts(new HashSet<>())
                                                .userId(orderRequest.getUserId())
                                                .orderDate(LocalDate.now())
                                                .status(Status.AWAITING_PAYMENT)
                                                .build());

        var newOrderProduct = OrderProduct.builder()
                                          .pk(OrderProductPK.builder()
                                                            .productId(orderRequest.getProductId())
                                                            .build())
                                          .quantity(orderRequest.getQuantity())
                                          .build();

        var orderProduct = order.getOrderProducts()
                                .stream()
                                .filter(op -> op.equals(newOrderProduct))
                                .findFirst();

        if (orderProduct.isPresent()) {
            orderProduct.get().setQuantity(orderProduct.get().getQuantity() + newOrderProduct.getQuantity());
        } else {
            order.getOrderProducts().add(newOrderProduct);
        }

        var savedOrder = orderRepository.save(order);
        log.info("Product added in order successfully");

        return mapper.toOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrder(Long userId) {
        log.info("getOrder is called");

        var order = orderRepository.findByUserIdAndStatus(userId, Status.AWAITING_PAYMENT)
                                   .orElseThrow(() -> new NotFoundException(
                                           String.format("Заказ пользователя с id=%s не найден", userId)
                                   ));

        log.info("Order got successfully");

        return mapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse pay(Long userId) {
        var order = orderRepository.findByUserIdAndStatus(userId, Status.AWAITING_PAYMENT)
                                   .orElseThrow(() -> new NotFoundException(
                                           String.format("Заказ пользователя с id=%s не найден", userId)
                                   ));

        // запрос на количество товара на складе
        // уменьшение количества товара на складе
        order.setStatus(Status.PAID);

        return mapper.toOrderResponse(order);
    }

    @Override
    public void deleteProduct(Long userId, Long productId) {
        log.info("deleteProduct is called");

        orderProductRepository.deleteOrderProductByProductId(productId, userId);

        log.info("Product deleted successfully");
    }

    @Override
    public void clear(Long userId) {
        log.info("clear is called");

        orderRepository.deleteByUserId(userId);

        log.info("Order cleared successfully");
    }
}
