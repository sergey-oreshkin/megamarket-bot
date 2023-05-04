package group.megamarket.storageservice.service.impl;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.exception.ProductAlreadyExistException;
import group.megamarket.storageservice.mapper.ProductMapper;
import group.megamarket.storageservice.model.Product;
import group.megamarket.storageservice.model.Role;
import group.megamarket.storageservice.repository.ProductRepository;
import group.megamarket.storageservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StorageServiceImplTest {

    public static final long VALID_USER_ID = 1L;
    public static final String VALID_PRODUCT_NAME = "product";
    public static final long TEST_PRODUCT_ID = 1L;
    public static final int TEST_PRODUCT_COUNT = 3;
    public static Product TEST_PRODUCT;
    public static ProductDto TEST_PRODUCT_DTO;

    @Mock
    UserServiceImpl userService;
    @Mock
    ProductRepository productRepository;
    @Mock
    ProductServiceImpl productService;
    @Spy
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    @InjectMocks
    StorageServiceImpl storageService;

    @BeforeEach
    void init(){
        TEST_PRODUCT = new Product(TEST_PRODUCT_ID, VALID_PRODUCT_NAME, TEST_PRODUCT_COUNT, VALID_USER_ID);
        TEST_PRODUCT_DTO = productMapper.toDto(TEST_PRODUCT);
    }

    @Test
    void getAll_shouldReturnTheSameAsRepository() {
        when(productRepository.findAll()).thenReturn(List.of(TEST_PRODUCT));

        List<ProductDto> products = storageService.getAll();

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertAll(
                ()->assertEquals(TEST_PRODUCT_ID, products.get(0).getId()),
                ()->assertEquals(TEST_PRODUCT_COUNT, products.get(0).getCount()),
                ()->assertEquals(VALID_PRODUCT_NAME, products.get(0).getName())
        );
    }

    @Test
    void getAll_shouldReturnEmptyList_whenRepositoryReturnEmptyList(){
        when(productRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<ProductDto> products = storageService.getAll();

        assertNotNull(products);
        assertTrue(products.isEmpty());
    }

    @Test
    void getAllByUserId_shouldReturnTheSameAsRepository() {
        when(productRepository.findAllByUserId(VALID_USER_ID)).thenReturn(List.of(TEST_PRODUCT));

        List<ProductDto> products = storageService.getAllByUserId(VALID_USER_ID);

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertAll(
                ()->assertEquals(TEST_PRODUCT_ID, products.get(0).getId()),
                ()->assertEquals(TEST_PRODUCT_COUNT, products.get(0).getCount()),
                ()->assertEquals(VALID_PRODUCT_NAME, products.get(0).getName())
        );

        verify(userService, atLeast(1)).checkUserHasRoleOrThrow(VALID_USER_ID, Role.SELLER);
    }

    @Test
    void deleteAllByUserId_shouldInvokeRepositoryDeleteByUserId() {
        storageService.deleteAllByUserId(VALID_USER_ID);

        verify(productRepository, times(1)).deleteByUserId(VALID_USER_ID);
    }

    @Test
    void addNewProduct_shouldReturnSameAsRepository() {
        when(productRepository.findByUserIdAndName(VALID_USER_ID,VALID_PRODUCT_NAME)).thenReturn(Optional.empty());
        when(productRepository.save(any())).thenReturn(TEST_PRODUCT);

        ProductDto productDto = storageService.addNewProduct(VALID_USER_ID, VALID_PRODUCT_NAME, TEST_PRODUCT_COUNT);

        assertNotNull(productDto);
        assertAll(
                ()->assertEquals(TEST_PRODUCT_ID, productDto.getId()),
                ()->assertEquals(TEST_PRODUCT_COUNT, productDto.getCount()),
                ()->assertEquals(VALID_PRODUCT_NAME, productDto.getName())
        );

        verify(userService, atLeast(1)).checkUserHasRoleOrThrow(VALID_USER_ID, Role.SELLER);
    }

    @Test
    void addNewProduct_shouldThrowProductAlreadyExistException_whenProductPresentInDb(){
        when(productRepository.findByUserIdAndName(VALID_USER_ID,VALID_PRODUCT_NAME)).thenReturn(Optional.of(TEST_PRODUCT));

        assertThrows(ProductAlreadyExistException.class, ()->storageService.addNewProduct(VALID_USER_ID,VALID_PRODUCT_NAME, TEST_PRODUCT_COUNT));

        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void changeProductCountBySeller_shouldReturnSameAsProductService() {
        when(productService.changeProductCount(VALID_USER_ID, VALID_PRODUCT_NAME, 1)).thenReturn(TEST_PRODUCT);
        ProductDto productDto = storageService.changeProductCountBySeller(VALID_USER_ID, VALID_PRODUCT_NAME, 1);

        assertNotNull(productDto);
        assertAll(
                ()->assertEquals(TEST_PRODUCT_ID, productDto.getId()),
                ()->assertEquals(TEST_PRODUCT_COUNT, productDto.getCount()),
                ()->assertEquals(VALID_PRODUCT_NAME, productDto.getName())
        );

        verify(userService, atLeast(1)).checkUserHasRoleOrThrow(VALID_USER_ID, Role.SELLER);
    }

    @Test
    void changeProductCountByBuyer_shouldInvokeProductServiceChangeProductsCount() {
        storageService.changeProductCountByBuyer(List.of(TEST_PRODUCT_DTO));

        verify(productService, times(1)).changeProductsCount(List.of(TEST_PRODUCT_DTO));
    }
}