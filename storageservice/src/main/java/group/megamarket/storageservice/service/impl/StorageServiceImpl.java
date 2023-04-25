package group.megamarket.storageservice.service.impl;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.exception.ProductAlreadyExistException;
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

    @Override
    public List<ProductDto> getAll() {
        return productMapper.toDto(productRepository.findAll());
    }

    @Override
    public List<ProductDto> getAllByUserId(Long userId) {
        userService.checkUserHasRoleOrThrow(userId, Role.SELLER);
        return productMapper.toDto(productRepository.findAllByUserId(userId));
    }

    @Override
    public Long deleteAllByUserId(Long userId) {
        return productRepository.deleteByUserId(userId);
    }

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

    @Override
    public ProductDto changeProductCountBySeller(Long userId, String productName, Integer difference) {
        userService.checkUserHasRoleOrThrow(userId, Role.SELLER);
        return productMapper.toDto(productService.changeProductCount(userId, productName, difference));
    }

    @Override
    public void changeProductCountByBuyer(List<ProductDto> products) {
        productService.changeProductsCount(products);
    }
}
