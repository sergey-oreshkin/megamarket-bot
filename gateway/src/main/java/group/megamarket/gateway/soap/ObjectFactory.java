package group.megamarket.gateway.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the group.megamarket.gateway.soap package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddNewProduct_QNAME = new QName("http://localhost:8000/soap", "addNewProduct");
    private final static QName _AddNewProductResponse_QNAME = new QName("http://localhost:8000/soap", "addNewProductResponse");
    private final static QName _ChangeProductCountByBuyer_QNAME = new QName("http://localhost:8000/soap", "changeProductCountByBuyer");
    private final static QName _ChangeProductCountByBuyerResponse_QNAME = new QName("http://localhost:8000/soap", "changeProductCountByBuyerResponse");
    private final static QName _ChangeProductCountBySeller_QNAME = new QName("http://localhost:8000/soap", "changeProductCountBySeller");
    private final static QName _ChangeProductCountBySellerResponse_QNAME = new QName("http://localhost:8000/soap", "changeProductCountBySellerResponse");
    private final static QName _DeleteAllByUserId_QNAME = new QName("http://localhost:8000/soap", "deleteAllByUserId");
    private final static QName _DeleteAllByUserIdResponse_QNAME = new QName("http://localhost:8000/soap", "deleteAllByUserIdResponse");
    private final static QName _GetAll_QNAME = new QName("http://localhost:8000/soap", "getAll");
    private final static QName _GetAllByUserId_QNAME = new QName("http://localhost:8000/soap", "getAllByUserId");
    private final static QName _GetAllByUserIdResponse_QNAME = new QName("http://localhost:8000/soap", "getAllByUserIdResponse");
    private final static QName _GetAllResponse_QNAME = new QName("http://localhost:8000/soap", "getAllResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: group.megamarket.gateway.soap
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddNewProduct }
     */
    public AddNewProduct createAddNewProduct() {
        return new AddNewProduct();
    }

    /**
     * Create an instance of {@link AddNewProductResponse }
     */
    public AddNewProductResponse createAddNewProductResponse() {
        return new AddNewProductResponse();
    }

    /**
     * Create an instance of {@link ChangeProductCountByBuyer }
     */
    public ChangeProductCountByBuyer createChangeProductCountByBuyer() {
        return new ChangeProductCountByBuyer();
    }

    /**
     * Create an instance of {@link ChangeProductCountByBuyerResponse }
     */
    public ChangeProductCountByBuyerResponse createChangeProductCountByBuyerResponse() {
        return new ChangeProductCountByBuyerResponse();
    }

    /**
     * Create an instance of {@link ChangeProductCountBySeller }
     */
    public ChangeProductCountBySeller createChangeProductCountBySeller() {
        return new ChangeProductCountBySeller();
    }

    /**
     * Create an instance of {@link ChangeProductCountBySellerResponse }
     */
    public ChangeProductCountBySellerResponse createChangeProductCountBySellerResponse() {
        return new ChangeProductCountBySellerResponse();
    }

    /**
     * Create an instance of {@link DeleteAllByUserId }
     */
    public DeleteAllByUserId createDeleteAllByUserId() {
        return new DeleteAllByUserId();
    }

    /**
     * Create an instance of {@link DeleteAllByUserIdResponse }
     */
    public DeleteAllByUserIdResponse createDeleteAllByUserIdResponse() {
        return new DeleteAllByUserIdResponse();
    }

    /**
     * Create an instance of {@link GetAll }
     */
    public GetAll createGetAll() {
        return new GetAll();
    }

    /**
     * Create an instance of {@link GetAllByUserId }
     */
    public GetAllByUserId createGetAllByUserId() {
        return new GetAllByUserId();
    }

    /**
     * Create an instance of {@link GetAllByUserIdResponse }
     */
    public GetAllByUserIdResponse createGetAllByUserIdResponse() {
        return new GetAllByUserIdResponse();
    }

    /**
     * Create an instance of {@link GetAllResponse }
     */
    public GetAllResponse createGetAllResponse() {
        return new GetAllResponse();
    }

    /**
     * Create an instance of {@link ProductDto }
     */
    public ProductDto createProductDto() {
        return new ProductDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewProduct }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link AddNewProduct }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "addNewProduct")
    public JAXBElement<AddNewProduct> createAddNewProduct(AddNewProduct value) {
        return new JAXBElement<AddNewProduct>(_AddNewProduct_QNAME, AddNewProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewProductResponse }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link AddNewProductResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "addNewProductResponse")
    public JAXBElement<AddNewProductResponse> createAddNewProductResponse(AddNewProductResponse value) {
        return new JAXBElement<AddNewProductResponse>(_AddNewProductResponse_QNAME, AddNewProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeProductCountByBuyer }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link ChangeProductCountByBuyer }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "changeProductCountByBuyer")
    public JAXBElement<ChangeProductCountByBuyer> createChangeProductCountByBuyer(ChangeProductCountByBuyer value) {
        return new JAXBElement<ChangeProductCountByBuyer>(_ChangeProductCountByBuyer_QNAME, ChangeProductCountByBuyer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeProductCountByBuyerResponse }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link ChangeProductCountByBuyerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "changeProductCountByBuyerResponse")
    public JAXBElement<ChangeProductCountByBuyerResponse> createChangeProductCountByBuyerResponse(ChangeProductCountByBuyerResponse value) {
        return new JAXBElement<ChangeProductCountByBuyerResponse>(_ChangeProductCountByBuyerResponse_QNAME, ChangeProductCountByBuyerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeProductCountBySeller }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link ChangeProductCountBySeller }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "changeProductCountBySeller")
    public JAXBElement<ChangeProductCountBySeller> createChangeProductCountBySeller(ChangeProductCountBySeller value) {
        return new JAXBElement<ChangeProductCountBySeller>(_ChangeProductCountBySeller_QNAME, ChangeProductCountBySeller.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeProductCountBySellerResponse }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link ChangeProductCountBySellerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "changeProductCountBySellerResponse")
    public JAXBElement<ChangeProductCountBySellerResponse> createChangeProductCountBySellerResponse(ChangeProductCountBySellerResponse value) {
        return new JAXBElement<ChangeProductCountBySellerResponse>(_ChangeProductCountBySellerResponse_QNAME, ChangeProductCountBySellerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllByUserId }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link DeleteAllByUserId }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "deleteAllByUserId")
    public JAXBElement<DeleteAllByUserId> createDeleteAllByUserId(DeleteAllByUserId value) {
        return new JAXBElement<DeleteAllByUserId>(_DeleteAllByUserId_QNAME, DeleteAllByUserId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllByUserIdResponse }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link DeleteAllByUserIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "deleteAllByUserIdResponse")
    public JAXBElement<DeleteAllByUserIdResponse> createDeleteAllByUserIdResponse(DeleteAllByUserIdResponse value) {
        return new JAXBElement<DeleteAllByUserIdResponse>(_DeleteAllByUserIdResponse_QNAME, DeleteAllByUserIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAll }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link GetAll }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "getAll")
    public JAXBElement<GetAll> createGetAll(GetAll value) {
        return new JAXBElement<GetAll>(_GetAll_QNAME, GetAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllByUserId }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link GetAllByUserId }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "getAllByUserId")
    public JAXBElement<GetAllByUserId> createGetAllByUserId(GetAllByUserId value) {
        return new JAXBElement<GetAllByUserId>(_GetAllByUserId_QNAME, GetAllByUserId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllByUserIdResponse }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link GetAllByUserIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "getAllByUserIdResponse")
    public JAXBElement<GetAllByUserIdResponse> createGetAllByUserIdResponse(GetAllByUserIdResponse value) {
        return new JAXBElement<GetAllByUserIdResponse>(_GetAllByUserIdResponse_QNAME, GetAllByUserIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllResponse }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link GetAllResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://localhost:8000/soap", name = "getAllResponse")
    public JAXBElement<GetAllResponse> createGetAllResponse(GetAllResponse value) {
        return new JAXBElement<GetAllResponse>(_GetAllResponse_QNAME, GetAllResponse.class, null, value);
    }

}
