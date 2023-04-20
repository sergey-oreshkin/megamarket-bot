package group.megamarket.storageservice.service;

import group.megamarket.storageservice.dto.ProductDto;
import group.megamarket.storageservice.server.Server;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = Server.DEFAULT_ADDRESS, name = "StorageService")
public interface StorageService {

    List<ProductDto> getAll();

    List<ProductDto> getAllByUserId(@WebParam(name = "userId", targetNamespace = Server.DEFAULT_ADDRESS) Long userId);

    Long deleteAllByUserId(@WebParam(name = "user", targetNamespace = Server.DEFAULT_ADDRESS) Long userId);

    ProductDto addNewProduct(@WebParam(name = "user", targetNamespace = Server.DEFAULT_ADDRESS) Long userId,
                             @WebParam(name = "product", targetNamespace = Server.DEFAULT_ADDRESS) String productName,
                             @WebParam(name = "count", targetNamespace = Server.DEFAULT_ADDRESS) Integer count);

    ProductDto changeProductCountBySeller(@WebParam(name = "user", targetNamespace = Server.DEFAULT_ADDRESS) Long userId,
                                          @WebParam(name = "product", targetNamespace = Server.DEFAULT_ADDRESS) String productName,
                                          @WebParam(name = "difference", targetNamespace = Server.DEFAULT_ADDRESS) Integer difference);

    void changeProductCountByBuyer(@WebParam(name = "products", targetNamespace = Server.DEFAULT_ADDRESS) List<ProductDto> products);
}
