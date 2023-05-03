package group.megamarket.marketservice.service;

import group.megamarket.marketservice.dto.OrderRequestDto;
import group.megamarket.marketservice.dto.OrderResponseDto;
import group.megamarket.marketservice.entity.Order;
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
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final StorageService storageService;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;


    @Override
    public OrderResponseDto addProduct(OrderRequestDto orderRequestDto, Long userId) {
        log.info("addProduct is called");
        var order = getOrCreateOrder(userId);
        addQuantity(orderRequestDto, userId, order);

        order = getOrderOrElseThrow(userId);
        log.info("Product added in order successfully");

        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponseDto getOrder(Long userId) {
        log.info("getOrder is called");

        var order = getOrderOrElseThrow(userId);

        log.info("Order got successfully");

        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponseDto pay(Long userId) {
        log.info("pay is called");

        var order = getOrderOrElseThrow(userId);

        var productsDto = productMapper.toProductDto(List.copyOf(order.getOrderProducts()));

        try {
            storageService.changeProductCountByBuyer(productsDto);
        } catch (Exception e) {
            log.error("Error when paying order user_id={userId}");
            throw new BadRequestException("Ошибка при оплате заказа");
        }

        order.setStatus(Status.PAID);

        log.info("Order paid successfully");

        return orderMapper.toOrderResponse(order);
    }

    @Override
    public void deleteProduct(Long userId, Long productId) {
        log.info("deleteProduct is called");

        int result = orderProductRepository.deleteOrderProductByProductId(productId, userId);

        if (result != 1) {
            log.error("Error when deleting a product with id={productId}");
            throw new NotFoundException("Ошибка при удалении продукта id=" + productId);
        }

        log.info("Product deleted successfully");
    }

    @Override
    public void clear(Long userId) {
        log.info("clear is called");

        int result = orderRepository.deleteByUserId(userId);

        if (result != 1) {
            log.error("Error when deleting a user's order with user_id={userId}");
            throw new NotFoundException("Ошибка при удалении заказ пользователя id=" + userId);
        }

        log.info("Order cleared successfully");
    }


    private void addQuantity(OrderRequestDto orderRequestDto, Long userId, Order order) {
        var orderProduct = orderProductRepository.findByProductIdAndOrder_UserId(orderRequestDto.getProductId(), userId);

        if (orderProduct.isPresent()) {
            orderProduct.get().addQuantity(orderRequestDto.getQuantity());
        } else {
            var newOrderProduct = orderMapper.toOrderProduct(orderRequestDto);
            newOrderProduct.setOrder(order);
            orderProductRepository.save(newOrderProduct);
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

    private Order getOrderOrElseThrow(Long userId) {
        return orderRepository.findByUserIdAndStatus(userId, Status.AWAITING_PAYMENT)
                              .orElseThrow(() -> {
                                  log.error("user_id={userId} order was not found");
                                  throw new NotFoundException(
                                          String.format("Заказ пользователя с id=%s не найден", userId)
                                  );
                              });
    }
}
