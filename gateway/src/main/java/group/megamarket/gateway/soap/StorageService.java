package group.megamarket.gateway.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 */
@WebService(name = "StorageService", targetNamespace = "http://localhost:8000/soap")
@XmlSeeAlso({
        ObjectFactory.class
})
public interface StorageService {


    /**
     * @return returns java.util.List<group.megamarket.gateway.soap.ProductDto>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAll", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.GetAll")
    @ResponseWrapper(localName = "getAllResponse", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.GetAllResponse")
    @Action(input = "http://localhost:8000/soap/StorageService/getAllRequest", output = "http://localhost:8000/soap/StorageService/getAllResponse")
    public List<ProductDto> getAll();

    /**
     * @param userId
     * @return returns java.util.List<group.megamarket.gateway.soap.ProductDto>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllByUserId", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.GetAllByUserId")
    @ResponseWrapper(localName = "getAllByUserIdResponse", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.GetAllByUserIdResponse")
    @Action(input = "http://localhost:8000/soap/StorageService/getAllByUserIdRequest", output = "http://localhost:8000/soap/StorageService/getAllByUserIdResponse")
    public List<ProductDto> getAllByUserId(
            @WebParam(name = "userId", targetNamespace = "http://localhost:8000/soap")
            Long userId);

    /**
     * @param userId
     * @return returns java.lang.Long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteAllByUserId", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.DeleteAllByUserId")
    @ResponseWrapper(localName = "deleteAllByUserIdResponse", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.DeleteAllByUserIdResponse")
    @Action(input = "http://localhost:8000/soap/StorageService/deleteAllByUserIdRequest", output = "http://localhost:8000/soap/StorageService/deleteAllByUserIdResponse")
    public Long deleteAllByUserId(
            @WebParam(name = "userId", targetNamespace = "http://localhost:8000/soap")
            Long userId);

    /**
     * @param count
     * @param userId
     * @param productName
     * @return returns group.megamarket.gateway.soap.ProductDto
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addNewProduct", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.AddNewProduct")
    @ResponseWrapper(localName = "addNewProductResponse", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.AddNewProductResponse")
    @Action(input = "http://localhost:8000/soap/StorageService/addNewProductRequest", output = "http://localhost:8000/soap/StorageService/addNewProductResponse")
    public ProductDto addNewProduct(
            @WebParam(name = "userId", targetNamespace = "http://localhost:8000/soap")
            Long userId,
            @WebParam(name = "productName", targetNamespace = "http://localhost:8000/soap")
            String productName,
            @WebParam(name = "count", targetNamespace = "http://localhost:8000/soap")
            Integer count);

    /**
     * @param difference
     * @param userId
     * @param productName
     * @return returns group.megamarket.gateway.soap.ProductDto
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "changeProductCountBySeller", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.ChangeProductCountBySeller")
    @ResponseWrapper(localName = "changeProductCountBySellerResponse", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.ChangeProductCountBySellerResponse")
    @Action(input = "http://localhost:8000/soap/StorageService/changeProductCountBySellerRequest", output = "http://localhost:8000/soap/StorageService/changeProductCountBySellerResponse")
    public ProductDto changeProductCountBySeller(
            @WebParam(name = "userId", targetNamespace = "http://localhost:8000/soap")
            Long userId,
            @WebParam(name = "productName", targetNamespace = "http://localhost:8000/soap")
            String productName,
            @WebParam(name = "difference", targetNamespace = "http://localhost:8000/soap")
            Integer difference);

    /**
     * @param products
     */
    @WebMethod
    @RequestWrapper(localName = "changeProductCountByBuyer", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.ChangeProductCountByBuyer")
    @ResponseWrapper(localName = "changeProductCountByBuyerResponse", targetNamespace = "http://localhost:8000/soap", className = "group.megamarket.gateway.soap.ChangeProductCountByBuyerResponse")
    @Action(input = "http://localhost:8000/soap/StorageService/changeProductCountByBuyerRequest", output = "http://localhost:8000/soap/StorageService/changeProductCountByBuyerResponse")
    public void changeProductCountByBuyer(
            @WebParam(name = "products", targetNamespace = "http://localhost:8000/soap")
            List<ProductDto> products);

}
