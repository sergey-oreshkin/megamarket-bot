package group.megamarket.storageservice.service.impl;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.exception.NotEnoughProductException;
import group.megamarket.storageservice.exception.ProductNotFoundException;
import group.megamarket.storageservice.model.Product;
import group.megamarket.storageservice.repository.ProductRepository;
import group.megamarket.storageservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    public static final String PRODUCT_NOT_FOUND_MESSAGE_TEMPLATE =
            "Product with name=%s not found.";

    public static final String PRODUCT_NOT_ENOUGH_MESSAGE_TEMPLATE =
            "Product with name=%s out of stocks";

    private final ProductRepository productRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Product changeProductCount(Long userId, String productName, int difference) {
        Product targetProduct = productRepository.findByUserIdAndName(userId, productName)
                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE_TEMPLATE, productName)));

        targetProduct.setCount(targetProduct.getCount() + difference);

        if (targetProduct.getCount() <= 0) {
            productRepository.delete(targetProduct);
            return targetProduct;
        }
        return productRepository.save(targetProduct);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void changeProductsCount(List<ProductDto> products) {
        for (ProductDto productDto : products) {
            Product targetProduct = productRepository.findById(productDto.getId())
                    .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE_TEMPLATE, productDto.getName())));
            int productRemain = targetProduct.getCount() - productDto.getCount();
            if (productRemain < 0) {
                throw new NotEnoughProductException(String.format(PRODUCT_NOT_ENOUGH_MESSAGE_TEMPLATE, productDto.getName()));
            }
            targetProduct.setCount(productRemain);

            if (targetProduct.getCount() == 0) {
                productRepository.delete(targetProduct);
            }
            productRepository.save(targetProduct);
        }
    }
}
