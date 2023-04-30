package group.megamarket.marketservice.mapper;

import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.soap.ProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ModelMapper mapper;

    public List<ProductDto> toProductDto(List<OrderProduct> orderProduct) {
        return orderProduct.stream()
                           .map(op -> mapper.map(op, ProductDto.class))
                           .collect(Collectors.toList());
    }

}
