package group.megamarket.storageservice.service;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.exception.UserNotFoundException;
import group.megamarket.storageservice.server.Server;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = Server.DEFAULT_NAMESPACE, name = "StorageService")
public interface StorageService {

    List<ProductDto> getAll();

    List<ProductDto> getAllByUserId(@WebParam(name = "userId", targetNamespace = Server.DEFAULT_NAMESPACE) Long userId) throws UserNotFoundException;

    Long deleteAllByUserId(@WebParam(name = "userId", targetNamespace = Server.DEFAULT_NAMESPACE) Long userId);

    ProductDto addNewProduct(@WebParam(name = "userId", targetNamespace = Server.DEFAULT_NAMESPACE) Long userId,
                             @WebParam(name = "productName", targetNamespace = Server.DEFAULT_NAMESPACE) String productName,
                             @WebParam(name = "count", targetNamespace = Server.DEFAULT_NAMESPACE) Integer count);

    ProductDto changeProductCountBySeller(@WebParam(name = "userId", targetNamespace = Server.DEFAULT_NAMESPACE) Long userId,
                                          @WebParam(name = "productName", targetNamespace = Server.DEFAULT_NAMESPACE) String productName,
                                          @WebParam(name = "difference", targetNamespace = Server.DEFAULT_NAMESPACE) Integer difference);

    void changeProductCountByBuyer(@WebParam(name = "products", targetNamespace = Server.DEFAULT_NAMESPACE) List<ProductDto> products);
}
