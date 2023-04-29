package group.megamarket.storageservice.service;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.model.Product;

import java.util.List;

public interface ProductService {
    Product changeProductCount(Long userId, String productName, int difference);

    void changeProductsCount(List<ProductDto> products);
}
