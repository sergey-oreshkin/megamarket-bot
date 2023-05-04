package group.megamarket.marketservice.service;


import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.exception.NotFoundException;
import group.megamarket.marketservice.mapper.ProductMapper;
import group.megamarket.marketservice.repo.OrderProductRepository;
import group.megamarket.marketservice.repo.OrderRepository;
import group.megamarket.marketservice.soap.ProductDto;
import group.megamarket.marketservice.soap.StorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static group.megamarket.marketservice.entity.Status.AWAITING_PAYMENT;
import static group.megamarket.marketservice.entity.Status.PAID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/*
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderProductRepository orderProductRepository;

    @Mock
    StorageService storageService;

    @Mock
    ProductMapper productMapper;

    @InjectMocks
    OrderServiceImpl orderService;

    private OrderProduct orderProduct;
    private OrderProductPK orderProductPK;
    private Order order;

    private static final Long PRODUCT_ID = 1L;
    private static final Long USER_ID = 20L;
    private static final Long ORDER_ID = 10L;
    private static final Integer QUANTITY = 10;

    @BeforeEach
    public void setup() {
        orderProductPK = OrderProductPK.builder()
                                       .productId(PRODUCT_ID)
                                       .build();

        orderProduct = OrderProduct.builder()
                                   .quantity(QUANTITY)
                                   .pk(orderProductPK)
                                   .build();

        order = Order.builder()
                     .id(ORDER_ID)
                     .status(AWAITING_PAYMENT)
                     .orderDate(LocalDate.now())
                     .userId(USER_ID)
                     .orderProducts(new HashSet<>())
                     .build();

        order.getOrderProducts().add(orderProduct);
    }

    @Test
    public void givenOrderProductAndUserId_whenAddProductInNewOrder_thenReturnOrder() {
        when(orderRepository.findByUserIdAndStatus(anyLong(), any())).thenReturn(Optional.empty());
        when(orderRepository.save(any())).thenReturn(order);

        Order result = orderService.addProduct(orderProduct, USER_ID);

        verify(orderRepository, times(1)).save(any());
        verify(orderRepository, times(1)).findByUserIdAndStatus(anyLong(), any());

        assertEquals(ORDER_ID, result.getId());
        assertEquals(USER_ID, result.getUserId());
        assertEquals(LocalDate.now(), result.getOrderDate());
        assertEquals(AWAITING_PAYMENT, result.getStatus());
        assertFalse(result.getOrderProducts().isEmpty());
        assertTrue(result.getOrderProducts().contains(orderProduct));
    }

    @Test
    public void givenOrderProductAndUserId_whenAddExistProductInExistOrder_thenReturnOrder() {
        var newQuantity = orderProduct.getQuantity() + QUANTITY;

        when(orderRepository.findByUserIdAndStatus(anyLong(), any())).thenReturn(Optional.of(order));
        when(orderRepository.save(any())).thenReturn(order);

        Order result = orderService.addProduct(orderProduct, USER_ID);

        verify(orderRepository, times(1)).save(any());
        verify(orderRepository, times(1)).findByUserIdAndStatus(anyLong(), any());

        assertEquals(ORDER_ID, result.getId());
        assertEquals(USER_ID, result.getUserId());
        assertEquals(LocalDate.now(), result.getOrderDate());
        assertEquals(AWAITING_PAYMENT, result.getStatus());
        assertFalse(result.getOrderProducts().isEmpty());
        assertTrue(result.getOrderProducts().contains(orderProduct));
        assertEquals(newQuantity, result.getOrderProducts().stream().findFirst().get().getQuantity());
    }

    @Test
    public void givenOrderProductAndUserId_whenAddNewProductInExistOrder_thenReturnOrder() {
        var newOrderProductPK = OrderProductPK.builder()
                                              .productId(90L)
                                              .build();
        var newOrderProduct = OrderProduct.builder()
                                          .pk(newOrderProductPK)
                                          .quantity(100)
                                          .build();

        when(orderRepository.findByUserIdAndStatus(anyLong(), any())).thenReturn(Optional.of(order));
        when(orderRepository.save(any())).thenReturn(order);

        Order result = orderService.addProduct(newOrderProduct, USER_ID);

        verify(orderRepository, times(1)).save(any());
        verify(orderRepository, times(1)).findByUserIdAndStatus(anyLong(), any());

        assertEquals(ORDER_ID, result.getId());
        assertEquals(USER_ID, result.getUserId());
        assertEquals(LocalDate.now(), result.getOrderDate());
        assertEquals(AWAITING_PAYMENT, result.getStatus());
        assertFalse(result.getOrderProducts().isEmpty());
        assertTrue(result.getOrderProducts().contains(newOrderProduct));
        assertEquals(2, result.getOrderProducts().size());
    }

    @Test
    public void givenUserId_whenGetExistOrder_thenReturnOrder() {
        when(orderRepository.findByUserIdAndStatus(anyLong(), any())).thenReturn(Optional.of(order));

        Order result = orderService.getOrder(USER_ID);

        verify(orderRepository, times(1)).findByUserIdAndStatus(anyLong(), any());

        assertEquals(ORDER_ID, result.getId());
        assertEquals(USER_ID, result.getUserId());
        assertEquals(LocalDate.now(), result.getOrderDate());
        assertEquals(AWAITING_PAYMENT, result.getStatus());
        assertFalse(result.getOrderProducts().isEmpty());
        assertTrue(result.getOrderProducts().contains(orderProduct));
        assertEquals(1, result.getOrderProducts().size());
    }

    @Test
    public void givenUserId_whenGetNotExistOrder_thenThrowNotFoundException() {
        when(orderRepository.findByUserIdAndStatus(anyLong(), any())).thenThrow(new NotFoundException(
                String.format("Заказ пользователя с id=%s не найден", USER_ID)
        ));

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> orderService.getOrder(USER_ID));

        assertEquals("Заказ пользователя с id=20 не найден", thrown.getMessage());
    }

    @Test
    public void givenUserId_whenPayExistOrder_thenReturnOrder() {
        var productDto = new ProductDto();
        productDto.setCount(QUANTITY);
        productDto.setId(PRODUCT_ID);
        productDto.setName("ProductName");

        when(orderRepository.findByUserIdAndStatus(anyLong(), any())).thenReturn(Optional.of(order));
        when(productMapper.toProductDto(any())).thenReturn(List.of(productDto));

        Order result = orderService.pay(USER_ID);

        verify(orderRepository, times(1)).findByUserIdAndStatus(anyLong(), any());

        assertEquals(ORDER_ID, result.getId());
        assertEquals(USER_ID, result.getUserId());
        assertEquals(LocalDate.now(), result.getOrderDate());
        assertEquals(PAID, result.getStatus());
        assertFalse(result.getOrderProducts().isEmpty());
        assertTrue(result.getOrderProducts().contains(orderProduct));
        assertEquals(1, result.getOrderProducts().size());
    }

    @Test
    public void givenUserId_whenPayNotExistOrder_thenThrowNotFoundException() {
        when(orderRepository.findByUserIdAndStatus(anyLong(), any())).thenThrow(new NotFoundException(
                String.format("Заказ пользователя с id=%s не найден", USER_ID)
        ));

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> orderService.pay(USER_ID));

        assertEquals("Заказ пользователя с id=20 не найден", thrown.getMessage());
    }

    @Test
    public void givenUserIdAndProductId_whenDeleteProductExistOrder() {
        when(orderProductRepository.deleteOrderProductByProductId(anyLong(), anyLong())).thenReturn(1);

        orderService.deleteProduct(USER_ID, PRODUCT_ID);

        verify(orderProductRepository, times(1)).deleteOrderProductByProductId(anyLong(), anyLong());
    }

    @Test
    public void givenUserIdAndProductId_whenDeleteProductNotExistOrder_thenThrowNotFoundException() {
        when(orderProductRepository.deleteOrderProductByProductId(anyLong(), anyLong())).thenThrow(
                new NotFoundException("Ошибка при удалении продукта id=" + PRODUCT_ID)
        );

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> orderService.deleteProduct(USER_ID, PRODUCT_ID));

        assertEquals("Ошибка при удалении продукта id=1", thrown.getMessage());
    }

    @Test
    public void givenUserId_whenClearExistOrder() {
        when(orderRepository.deleteByUserId(anyLong())).thenReturn(1);

        orderService.clear(USER_ID);

        verify(orderRepository, times(1)).deleteByUserId(anyLong());
    }

    @Test
    public void givenUserId_whenClearExistOrder_thenThrowNotFoundException() {
        when(orderRepository.deleteByUserId(anyLong())).thenThrow(
                new NotFoundException("Ошибка при удалении заказ пользователя id=" + USER_ID)
        );

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> orderService.clear(USER_ID));

        assertEquals("Ошибка при удалении заказ пользователя id=20", thrown.getMessage());
    }
}*/
