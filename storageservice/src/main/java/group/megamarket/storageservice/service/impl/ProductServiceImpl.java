package group.megamarket.storageservice.service.impl;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.exception.NotEnoughProductException;
import group.megamarket.storageservice.exception.ProductNotFoundException;
import group.megamarket.storageservice.model.Product;
import group.megamarket.storageservice.repository.ProductRepository;
import group.megamarket.storageservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с продуктами. Изменение количества и тп.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    public static final String PRODUCT_NOT_FOUND_MESSAGE_TEMPLATE =
            "Product with name=%s not found.";

    public static final String PRODUCT_NOT_ENOUGH_MESSAGE_TEMPLATE =
            "Product with name=%s out of stocks";

    private final ProductRepository productRepository;

    /**
     * @param userId      - id юзера
     * @param productName - название продукта
     * @param difference  - на сколько изменить количество(может быть отрицательным, чтоб уменьшить)
     * @return Product c измененным количеством
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Product changeProductCount(Long userId, String productName, int difference) {
        log.info("Change product count. userId={}, productName={}, difference={}", userId, productName, difference);
        Product targetProduct = productRepository.findByUserIdAndName(userId, productName)
                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE_TEMPLATE, productName)));

        targetProduct.setCount(targetProduct.getCount() + difference);

        if (targetProduct.getCount() <= 0) {
            log.info("Product count is negative, will be deleted. Product: {}", targetProduct);
            productRepository.delete(targetProduct);
            return targetProduct;
        }
        Product changedProduct = productRepository.save(targetProduct);
        log.info("Changed product: {}", changedProduct);
        return changedProduct;
    }

    /**
     * Уменьшает количество каждого продукта в списке на величину установленную в ProductDto#count
     *
     * @param products - список продуктов у которых надо изменить количество.
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void changeProductsCount(List<ProductDto> products) {
        log.info("Change product count. products: {}", products);
        for (ProductDto productDto : products) {
            Product targetProduct = productRepository.findById(productDto.getId())
                    .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE_TEMPLATE, productDto.getName())));
            int productRemain = targetProduct.getCount() - productDto.getCount();
            if (productRemain < 0) {
                log.error("Not enough product: {}", targetProduct);
                throw new NotEnoughProductException(String.format(PRODUCT_NOT_ENOUGH_MESSAGE_TEMPLATE, productDto.getName()));
            }
            targetProduct.setCount(productRemain);

            if (targetProduct.getCount() == 0) {
                log.info("Product count is zero, will be deleted. Product: {}", targetProduct);
                productRepository.delete(targetProduct);
            } else {
                log.info("Product count change successfully. Product: {}", targetProduct);
                productRepository.save(targetProduct);
            }
        }
    }
}
