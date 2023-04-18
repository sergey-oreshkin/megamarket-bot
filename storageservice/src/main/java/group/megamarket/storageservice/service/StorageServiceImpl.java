package group.megamarket.storageservice.service;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.exception.ProductAlreadyExistException;
import group.megamarket.storageservice.exception.ProductNotFoundException;
import group.megamarket.storageservice.exception.UserNotFoundException;
import group.megamarket.storageservice.mapper.ProductMapper;
import group.megamarket.storageservice.model.Product;
import group.megamarket.storageservice.model.Role;
import group.megamarket.storageservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@WebService(
        name = "StorageService",
        portName = "ProductPort",
        targetNamespace = "http://localhost:8000/storageservice",
        endpointInterface = "group.megamarket.storageservice.service.StorageService")
public class StorageServiceImpl implements StorageService {

    public static final String PRODUCT_ALREADY_EXIST_MESSAGE =
            "Product with name %s already exist. Use changeProductCount instead.";
    public static final String USER_NOT_FOUND_MESSAGE =
            "User with id=%d not found or not a seller.";
    public static final String PRODUCT_NOT_FOUND_MESSAGE =
            "Product with name=%s not found.";

    private final UserService userService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAll() {
        return productMapper.toDto(productRepository.findAll());
    }

    @Override
    public List<ProductDto> getAllByUserId(Long userId) {
        if (!userService.userHasRole(userId, Role.SELLER)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId));
        }
        return productMapper.toDto(productRepository.findAllByUserId(userId));
    }

    @Override
    public Long deleteAllByUserId(Long userId) {
        return productRepository.deleteByUserId(userId);
    }

    @Override
    public ProductDto addNewProduct(Long userId, String productName, Integer count) {
        if (!userService.userHasRole(userId, Role.SELLER)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId));
        }
        Optional<Product> productOptional = productRepository.findByUserIdAndName(userId, productName);
        if (productOptional.isPresent()) {
            throw new ProductAlreadyExistException(String.format(PRODUCT_ALREADY_EXIST_MESSAGE, productName));
        }
        Product product = new Product(null, productName, count, userId);

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto changeProductCount(Long userId, String productName, Integer difference) {
        if (!userService.userHasRole(userId, Role.SELLER)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId));
        }

        Product targetProduct = productRepository.findByUserIdAndName(userId, productName)
                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, productName)));

        targetProduct.setCount(targetProduct.getCount() + difference);

        if (targetProduct.getCount() <= 0) {
            productRepository.delete(targetProduct);
            return productMapper.toDto(targetProduct);
        }

        return productMapper.toDto(productRepository.save(targetProduct));
    }
}
