package group.megamarket.storageservice.mapper;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductDto toDto(Product product);

    List<ProductDto> toDto(List<Product> products);
}
