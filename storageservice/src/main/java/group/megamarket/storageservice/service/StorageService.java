package group.megamarket.storageservice.service;

import group.megamarket.storageservice.dto.ProductDto;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://localhost:8000/storageservice", name = "StorageService")
public interface StorageService {

    List<ProductDto> getAll();

    List<ProductDto> getAllByUserId(@WebParam(name = "userId", targetNamespace = "http://localhost:8000/storageservice") Long userId);

    Long deleteAllByUserId(@WebParam(name = "user", targetNamespace = "http://localhost:8000/storageservice") Long userId);

    ProductDto addNewProduct(@WebParam(name = "user", targetNamespace = "http://localhost:8000/storageservice") Long userId,
                             @WebParam(name = "product", targetNamespace = "http://localhost:8000/storageservice") String productName,
                             @WebParam(name = "count", targetNamespace = "http://localhost:8000/storageservice") Integer count);

    ProductDto changeProductCount(@WebParam(name = "user", targetNamespace = "http://localhost:8000/storageservice") Long userId,
                                  @WebParam(name = "product", targetNamespace = "http://localhost:8000/storageservice") String productName,
                                  @WebParam(name = "difference", targetNamespace = "http://localhost:8000/storageservice") Integer difference);
}
