package group.megamarket.marketservice.service;

import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.entity.Status;
import group.megamarket.marketservice.exception.BadRequestException;
import group.megamarket.marketservice.exception.NotFoundException;
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
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final StorageService storageService;
    private final ProductMapper productMapper;


    @Override
    public Order addProduct(OrderProduct newOrderProduct, Long userId) {
        log.info("addProduct is called");
        var order = getOrCreateOrder(userId);

        var orderProduct = order.getOrderProducts()
                                .stream()
                                .filter(op -> op.equals(newOrderProduct))
                                .findFirst();

        addQuantity(newOrderProduct, order, orderProduct);

        var savedOrder = orderRepository.save(order);
        log.info("Product added in order successfully");

        return savedOrder;
    }

    @Override
    public Order getOrder(Long userId) {
        log.info("getOrder is called");

        var order = orderRepository.findByUserIdAndStatus(userId, Status.AWAITING_PAYMENT)
                                   .orElseThrow(() -> new NotFoundException(
                                           String.format("Заказ пользователя с id=%s не найден", userId)
                                   ));

        log.info("Order got successfully");

        return order;
    }

    @Override
    public Order pay(Long userId) {
        log.info("pay is called");

        var order = orderRepository.findByUserIdAndStatus(userId, Status.AWAITING_PAYMENT)
                                   .orElseThrow(() -> new NotFoundException(
                                           String.format("Заказ пользователя с id=%s не найден", userId)
                                   ));

        var productsDto = productMapper.toProductDto(List.copyOf(order.getOrderProducts()));

        try {
            storageService.changeProductCountByBuyer(productsDto);
        } catch (Exception e) {
            throw new BadRequestException("Ошибка при оплате заказа");
        }

        order.setStatus(Status.PAID);

        log.info("Order paid successfully");

        return order;
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


    private void addQuantity(OrderProduct newOrderProduct, Order order, Optional<OrderProduct> orderProduct) {
        if (orderProduct.isPresent()) {
            orderProduct.get().setQuantity(orderProduct.get().getQuantity() + newOrderProduct.getQuantity());
        } else {
            order.getOrderProducts().add(newOrderProduct);
        }
    }

    private Order getOrCreateOrder(Long userId) {
        return orderRepository.findByUserIdAndStatus(userId, Status.AWAITING_PAYMENT)
                              .orElse(Order.builder()
                                           .orderProducts(new HashSet<>())
                                           .userId(userId)
                                           .orderDate(LocalDate.now())
                                           .status(Status.AWAITING_PAYMENT)
                                           .build());
    }
}
