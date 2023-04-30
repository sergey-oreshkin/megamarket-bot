package group.megamarket.marketservice.mapper;

import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.entity.OrderProductPK;
import group.megamarket.marketservice.soap.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

    private ProductMapper mapper;

    @BeforeEach
    void setUp() {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.STRICT)
                   .setSkipNullEnabled(true);

        modelMapper.typeMap(OrderProduct.class, ProductDto.class)
                   .addMappings(mapping -> {
                       mapping.map(op -> op.getPk().getProductId(), ProductDto::setId);
                       mapping.map(OrderProduct::getQuantity, ProductDto::setCount);
                   });

        mapper = new ProductMapper(modelMapper);
    }


    @Test
    void whenMapOrderProductWithExactMatch_thenConvertsToProductDTO() {
        var orderProduct = OrderProduct.builder()
                                       .pk(OrderProductPK.builder()
                                                         .productId(1L)
                                                         .build())
                                       .quantity(10)
                                       .build();

        var orderProducts = List.of(orderProduct);


        var productsDto = mapper.toProductDto(orderProducts);

        assertEquals(orderProduct.getPk().getProductId(), productsDto.get(0).getId());
        assertEquals(orderProduct.getQuantity(), productsDto.get(0).getCount());
    }
}