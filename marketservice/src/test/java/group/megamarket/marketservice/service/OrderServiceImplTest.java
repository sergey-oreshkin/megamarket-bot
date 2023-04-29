package group.megamarket.marketservice.service;


import group.megamarket.marketservice.dto.OrderRequest;
import group.megamarket.marketservice.dto.ProductDto;
import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.mapper.OrderMapper;
import group.megamarket.marketservice.repo.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository repository;
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OrderMapper mapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderRequest orderRequest;
    private Order order;
    private ProductDto productDto;

    @BeforeEach
    public void setup() {
        orderRequest = new OrderRequest(1L, 10, 1L);

        order = Order.builder()
                     .build();

        productDto = new ProductDto(1L, "name", 10);
    }

    @Test
    public void givenOrderRequest_whenAddProduct_thenReturnOrderResponse() {
      /*  Order savedOrder = order;
        order.setId(1L);
        order.setStatus(Status.AWAITING_PAYMENT);
        order.setOrderDate(LocalDate.now());

        when(restTemplate.getForObject(anyString(), any())).thenReturn(productDto);
        when(mapper.toOrderProduct(orderRequest)).thenReturn(order);
        when(repository.save(order)).thenReturn(savedOrder);

        OrderResponse orderResponse = orderService.addProduct(orderRequest);

        verify(repository, times(1)).save(savedOrder);*/
    }
}