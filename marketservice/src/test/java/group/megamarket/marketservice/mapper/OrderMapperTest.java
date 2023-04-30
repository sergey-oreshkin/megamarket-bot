package group.megamarket.marketservice.mapper;

import group.megamarket.marketservice.dto.OrderProductDto;
import group.megamarket.marketservice.dto.OrderRequestDto;
import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.entity.OrderProductPK;
import group.megamarket.marketservice.entity.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OrderMapperTest {
    private ModelMapper modelMapper;

    private OrderMapper mapper;


    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.STRICT)
                   .setSkipNullEnabled(true);

        var orderProductMap = modelMapper.typeMap(OrderProduct.class, OrderProductDto.class);
        orderProductMap.addMappings(mapping -> {
            mapping.map(op -> op.getPk().getProductId(), OrderProductDto::setProductId);
            mapping.map(OrderProduct::getQuantity, OrderProductDto::setQuantity);
        });

        var orderRequestDtoMapOrderProduct = modelMapper.typeMap(OrderRequestDto.class, OrderProduct.class);
        orderRequestDtoMapOrderProduct.addMappings(mapping -> {
            mapping.map(OrderRequestDto::getQuantity, OrderProduct::setQuantity);
            mapping.using(context -> new OrderProductPK(null, (Long) context.getSource()))
                   .map(OrderRequestDto::getProductId, OrderProduct::setPk);
        });

        mapper = new OrderMapper(modelMapper);
    }

    @Test
    void whenMapOrderRequestDto_thenConvertsToOrderProduct() {
        var orderRequestDto = new OrderRequestDto(1L, 10, 3L);

        var orderProduct = mapper.toOrderProduct(orderRequestDto);

        assertEquals(orderRequestDto.getProductId(), orderProduct.getPk().getProductId());
        assertEquals(orderRequestDto.getQuantity(), orderProduct.getQuantity());
    }

    @Test
    void whenMapOrder_thenConvertsToOrderResponseDto() {
        var orderProductPK = OrderProductPK.builder().productId(1L).build();

        var orderProduct = OrderProduct.builder().pk(orderProductPK).quantity(10).build();

        var order = Order.builder()
                         .orderDate(LocalDate.now())
                         .userId(1L)
                         .status(Status.AWAITING_PAYMENT)
                         .orderProducts(Set.of(orderProduct))
                         .build();

        var orderResponseDto = mapper.toOrderResponse(order);

        assertEquals(order.getOrderDate(), orderResponseDto.getOrderDate());
        assertEquals(order.getStatus(), orderResponseDto.getStatus());
        assertEquals(order.getUserId(), orderResponseDto.getUserId());
        assertFalse(orderResponseDto.getOrderProducts().isEmpty());
        assertEquals(orderProduct.getQuantity(), orderResponseDto.getOrderProducts().get(0).getQuantity());
        assertEquals(orderProduct.getPk().getProductId(), orderResponseDto.getOrderProducts().get(0).getProductId());
    }
}