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
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

/**
 * сервис для работы со складом.
 */
@Service
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
        return productMapper.toDto(productRepository.findAll());
    }

    /**
     * @param userId - id юзера
     * @return список всех доступных продуктов данного юзера
     * @throws UserNotFoundException - если юзер не найден
     */
    @Override
    public List<ProductDto> getAllByUserId(Long userId) {
        userService.checkUserHasRoleOrThrow(userId, Role.SELLER);
        return productMapper.toDto(productRepository.findAllByUserId(userId));
    }

    /**
     * Удаляет все продукты определенного юзера
     *
     * @param userId - id юзера
     * @return количество удаленных записей
     */
    @Override
    public Long deleteAllByUserId(Long userId) {
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
        userService.checkUserHasRoleOrThrow(userId, Role.SELLER);
        Optional<Product> productOptional = productRepository.findByUserIdAndName(userId, productName);
        if (productOptional.isPresent()) {
            throw new ProductAlreadyExistException(String.format(PRODUCT_ALREADY_EXIST_MESSAGE_TEMPLATE, productName));
        }
        Product product = new Product(null, productName, count, userId);

        return productMapper.toDto(productRepository.save(product));
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
        userService.checkUserHasRoleOrThrow(userId, Role.SELLER);
        return productMapper.toDto(productService.changeProductCount(userId, productName, difference));
    }

    /**
     * Уменьшает количество каждого продукта в списке на величину установленную в ProductDto#count
     *
     * @param products - список продуктов, количества которых нужно изменить
     */
    @Override
    public void changeProductCountByBuyer(List<ProductDto> products) {
        productService.changeProductsCount(products);
    }
}
