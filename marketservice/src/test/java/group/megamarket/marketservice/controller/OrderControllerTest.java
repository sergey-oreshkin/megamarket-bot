package group.megamarket.marketservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import group.megamarket.marketservice.dto.OrderProductDto;
import group.megamarket.marketservice.dto.OrderRequestDto;
import group.megamarket.marketservice.dto.OrderResponseDto;
import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.mapper.OrderMapper;
import group.megamarket.marketservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static group.megamarket.marketservice.entity.Status.AWAITING_PAYMENT;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderController controller;

    private final ObjectMapper mapper = new ObjectMapper();
    private MockMvc mvc;
    private OrderRequestDto orderRequestDto;
    private OrderResponseDto orderResponseDto;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

        orderRequestDto = new OrderRequestDto(1L, 10, 20L);
        orderResponseDto = new OrderResponseDto(
                null,
                20L,
                AWAITING_PAYMENT,
                List.of(new OrderProductDto(1L, 20)));
    }

    @Test
    void givenOrderProduct_whenAddProduct_thenReturnStatusOk() throws Exception {
        when(orderService.addProduct(any(), anyLong())).thenReturn(new Order());
        when(orderMapper.toOrderProduct(any())).thenReturn(new OrderProduct());
        when(orderMapper.toOrderResponse(any())).thenReturn(orderResponseDto);

        mvc.perform(put("/carts")
                   .content(mapper.writeValueAsString(orderResponseDto))
                   .characterEncoding(StandardCharsets.UTF_8)
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.userId", is(orderResponseDto.getUserId()), Long.class))
           .andExpect(jsonPath(
                   "$.orderProducts.[0].productId",
                   is(orderResponseDto.getOrderProducts().get(0).getProductId()), Long.class)
           );
    }

    @Test
    void givenUserId_whenGetOrder_thenReturnStatusOk() throws Exception {
        when(orderService.getOrder(anyLong())).thenReturn(new Order());
        when(orderMapper.toOrderResponse(any())).thenReturn(orderResponseDto);

        mvc.perform(get("/carts/users/20")
                   .content(mapper.writeValueAsString(orderResponseDto))
                   .characterEncoding(StandardCharsets.UTF_8)
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.userId", is(orderResponseDto.getUserId()), Long.class))
           .andExpect(jsonPath(
                   "$.orderProducts.[0].productId",
                   is(orderResponseDto.getOrderProducts().get(0).getProductId()), Long.class)
           );
    }

    @Test
    void givenUserId_whenPayOrder_thenReturnStatusOk() throws Exception {
        when(orderService.pay(anyLong())).thenReturn(new Order());
        when(orderMapper.toOrderResponse(any())).thenReturn(orderResponseDto);

        mvc.perform(post("/carts/users/20")
                   .content(mapper.writeValueAsString(orderResponseDto))
                   .characterEncoding(StandardCharsets.UTF_8)
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.userId", is(orderResponseDto.getUserId()), Long.class))
           .andExpect(jsonPath(
                   "$.orderProducts.[0].productId",
                   is(orderResponseDto.getOrderProducts().get(0).getProductId()), Long.class)
           );
    }

    @Test
    void givenUserIdAndProductId_whenDeleteProduct_thenReturnStatusOk() throws Exception {
        mvc.perform(delete("/carts/users/20/products/1")
                   .characterEncoding(StandardCharsets.UTF_8)
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
    }

    @Test
    void givenUserId_whenClearOrder_thenReturnStatusOk() throws Exception {
        mvc.perform(delete("/carts/users/20")
                   .characterEncoding(StandardCharsets.UTF_8)
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
    }
}