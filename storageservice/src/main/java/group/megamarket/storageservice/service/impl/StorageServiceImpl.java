package group.megamarket.storageservice.service.impl;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.exception.ProductAlreadyExistException;
import group.megamarket.storageservice.exception.UserNotFoundException;
import group.megamarket.storageservice.mapper.ProductMapper;
import group.megamarket.storageservice.model.Product;
import group.megamarket.storageservice.model.Role;
import group.megamarket.storageservice.repository.ProductRepository;
import group.megamarket.storageservice.server.Server;
import group.megamarket.storageservice.service.ProductService;
import group.megamarket.storageservice.service.StorageService;
import group.megamarket.storageservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

/**
 * сервис для работы со складом.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@WebService(
        name = "StorageService",
        portName = "ProductPort",
        targetNamespace = Server.DEFAULT_NAMESPACE,
        endpointInterface = "group.megamarket.storageservice.service.StorageService")
public class StorageServiceImpl implements StorageService {

    public static final String PRODUCT_ALREADY_EXIST_MESSAGE_TEMPLATE =
            "Product with name %s already exist. Use changeProductCount instead.";

    private final UserService userService;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductMapper productMapper;

    /**
     * @return список всех доступных продуктов
     */
    @Override
    public List<ProductDto> getAll() {
        log.info("Try to get all products");
        List<Product> products = productRepository.findAll();
        log.info("All products:{}", products);
        return productMapper.toDto(products);
    }

    /**
     * @param userId - id юзера
     * @return список всех доступных продуктов данного юзера
     * @throws UserNotFoundException - если юзер не найден
     */
    @Override
    public List<ProductDto> getAllByUserId(Long userId) {
        log.info("Try to get all by user id={}", userId);
        userService.checkUserHasRoleOrThrow(userId, Role.SELLER);
        List<Product> products = productRepository.findAllByUserId(userId);
        log.info("All by userid=" + userId + " " + products);
        return productMapper.toDto(products);
    }

    /**
     * Удаляет все продукты определенного юзера
     *
     * @param userId - id юзера
     * @return количество удаленных записей
     */
    @Override
    public Long deleteAllByUserId(Long userId) {
        log.info("Delete by user id={}", userId);
        return productRepository.deleteByUserId(userId);
    }

    /**
     * Добавляет в базу продукт
     *
     * @param userId      - id юзера
     * @param productName - название продукта
     * @param count       - количество
     * @return - ДТО продукта добавленного в базу
     * @throws UserNotFoundException - если юзер не найден
     */
    @Override
    public ProductDto addNewProduct(Long userId, String productName, Integer count) {
        log.info("Try to add new product. userId={}, productName={}, count={}", userId, productName, count);
        userService.checkUserHasRoleOrThrow(userId, Role.SELLER);
        Optional<Product> productOptional = productRepository.findByUserIdAndName(userId, productName);
        if (productOptional.isPresent()) {
            throw new ProductAlreadyExistException(String.format(PRODUCT_ALREADY_EXIST_MESSAGE_TEMPLATE, productName));
        }
        Product savedProduct = productRepository.save(new Product(null, productName, count, userId));
        log.info("Added product: {}", savedProduct);
        return productMapper.toDto(savedProduct);
    }

    /**
     * Изменяет количество существующего продукта на значение difference. Будет уменьшено если difference отрицательный.
     *
     * @param userId      - id юзера
     * @param productName - название продукта
     * @param difference  - разность количества
     * @return ДТО измененного продукта
     * @throws UserNotFoundException - если юзер не найден
     */
    @Override
    public ProductDto changeProductCountBySeller(Long userId, String productName, Integer difference) {
        log.info("Try to change product count. userId={}, productName={}, difference={}", userId, productName, difference);
        userService.checkUserHasRoleOrThrow(userId, Role.SELLER);
        Product changedProduct = productService.changeProductCount(userId, productName, difference);
        log.info("Changed product: {}", changedProduct);
        return productMapper.toDto(changedProduct);
    }

    /**
     * Уменьшает количество каждого продукта в списке на величину установленную в ProductDto#count
     *
     * @param products - список продуктов, количества которых нужно изменить
     */
    @Override
    public void changeProductCountByBuyer(List<ProductDto> products) {
        log.info("Try to change products count by buyer: {}", products);
        productService.changeProductsCount(products);
    }
}
