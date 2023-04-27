package group.megamarket.storageservice.service.impl;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.exception.NotEnoughProductException;
import group.megamarket.storageservice.exception.ProductNotFoundException;
import group.megamarket.storageservice.mapper.ProductMapper;
import group.megamarket.storageservice.model.Product;
import group.megamarket.storageservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    public static final long VALID_USER_ID = 1L;
    public static final String VALID_PRODUCT_NAME = "product";
    public static final long TEST_PRODUCT_ID = 1L;
    public static final int TEST_PRODUCT_COUNT = 3;
    public static Product TEST_PRODUCT;
    public static ProductDto TEST_PRODUCT_DTO;

    public static final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;
    @Captor
    ArgumentCaptor<Product> productCaptor;

    @BeforeEach
    void init() {
        TEST_PRODUCT = new Product(TEST_PRODUCT_ID, VALID_PRODUCT_NAME, TEST_PRODUCT_COUNT, VALID_USER_ID);
        TEST_PRODUCT_DTO = mapper.toDto(TEST_PRODUCT);
    }

    @Test
    void changeProductCount_shouldThrowProductNotFoundException_whenProductNotFound() {
        when(productRepository.findByUserIdAndName(anyLong(), anyString())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.changeProductCount(anyLong(), anyString(), 0));
    }

    @Test
    void changeProductCount_shouldChangeCountAndSaveWithNewCount() {
        when(productRepository.findByUserIdAndName(VALID_USER_ID, VALID_PRODUCT_NAME)).thenReturn(Optional.of(TEST_PRODUCT));
        when(productRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        Product product = productService.changeProductCount(VALID_USER_ID, VALID_PRODUCT_NAME, 1);

        assertNotNull(product);
        assertEquals(TEST_PRODUCT_COUNT + 1, product.getCount());
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void changeProductCount_shouldDeleteProduct_whenCountLessThenOne() {
        when(productRepository.findByUserIdAndName(VALID_USER_ID, VALID_PRODUCT_NAME)).thenReturn(Optional.of(TEST_PRODUCT));

        Product product = productService.changeProductCount(VALID_USER_ID, VALID_PRODUCT_NAME, -TEST_PRODUCT_COUNT);

        assertNotNull(product);
        assertEquals(0, product.getCount());
        verify(productRepository, times(1)).delete(any());
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void changeProductsCount_shouldThrowProductNotFoundException_whenProductNotFound() {
        when(productRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, ()->productService.changeProductsCount(List.of(TEST_PRODUCT_DTO)));
    }

    @Test
    void changeProductsCount_shouldChangeCountAndSaveWithNewCount() {
        when(productRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(TEST_PRODUCT));
        TEST_PRODUCT_DTO.setCount(1);
        productService.changeProductsCount(List.of(TEST_PRODUCT_DTO));

        verify(productRepository).save(productCaptor.capture());
        Product product = productCaptor.getValue();
        assertEquals(TEST_PRODUCT_COUNT -1, product.getCount());
    }

    @Test
    void changeProductsCount_shouldDeleteProduct_whenCountLessThenOne() {
        when(productRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(TEST_PRODUCT));
        TEST_PRODUCT_DTO.setCount(TEST_PRODUCT_COUNT); // for illustration
        productService.changeProductsCount(List.of(TEST_PRODUCT_DTO));

        verify(productRepository).delete(productCaptor.capture());
        Product product = productCaptor.getValue();

        assertEquals(0, product.getCount());

        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void changeProductsCount_shouldThrowNotEnoughProductException_whenTryToGetMoreThenAvailable(){
        when(productRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(TEST_PRODUCT));
        TEST_PRODUCT_DTO.setCount(TEST_PRODUCT_COUNT + 1);

        assertThrows(NotEnoughProductException.class, ()->productService.changeProductsCount(List.of(TEST_PRODUCT_DTO)));
    }
}