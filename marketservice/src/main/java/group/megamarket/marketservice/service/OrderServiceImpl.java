package group.megamarket.marketservice.service;

import group.megamarket.marketservice.dto.OrderRequest;
import group.megamarket.marketservice.dto.OrderResponse;
import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.entity.OrderProductPK;
import group.megamarket.marketservice.entity.Status;
import group.megamarket.marketservice.exception.BadRequestException;
import group.megamarket.marketservice.exception.NotFoundException;
import group.megamarket.marketservice.mapper.OrderMapper;
import group.megamarket.marketservice.mapper.ProductMapper;
import group.megamarket.marketservice.repo.OrderProductRepository;
import group.megamarket.marketservice.repo.OrderRepository;
import group.megamarket.marketservice.soap.StorageService;
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
    private final StorageService storageService;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    @Override
    public OrderResponse addProduct(OrderRequest orderRequest) {
        log.info("addProduct is called");
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

        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrder(Long userId) {
        log.info("getOrder is called");

        var order = orderRepository.findByUserIdAndStatus(userId, Status.AWAITING_PAYMENT)
                                   .orElseThrow(() -> new NotFoundException(
                                           String.format("Заказ пользователя с id=%s не найден", userId)
                                   ));

        log.info("Order got successfully");

        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse pay(Long userId) {
        log.info("pay is called");

        var order = orderRepository.findByUserIdAndStatus(userId, Status.AWAITING_PAYMENT)
                                   .orElseThrow(() -> new NotFoundException(
                                           String.format("Заказ пользователя с id=%s не найден", userId)
                                   ));

        var orderProducts = order.getOrderProducts();

        var productsDto = productMapper.toProductDto(orderProducts.stream().toList());

        try {
            storageService.changeProductCountByBuyer(productsDto);
        } catch (Exception e){
            throw new BadRequestException("Ошибка при оплате заказа");
        }

        order.setStatus(Status.PAID);

        log.info("Order paid successfully");

        return orderMapper.toOrderResponse(order);
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
