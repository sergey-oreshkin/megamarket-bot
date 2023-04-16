package group.megamarket.storageservice.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://storageservice", name = "StorageService")
public interface ProductService {

    void saveUser(@WebParam(name = "id", targetNamespace = "http://storageservice") Long id);
}
