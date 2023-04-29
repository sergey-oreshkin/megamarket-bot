package group.megamarket.userservice.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for changeProductCountBySeller complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="changeProductCountBySeller"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="difference" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "changeProductCountBySeller", propOrder = {
        "userId",
        "productName",
        "difference"
})
public class ChangeProductCountBySeller {

    protected Long userId;
    protected String productName;
    protected Integer difference;

    /**
     * Gets the value of the userId property.
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setUserId(Long value) {
        this.userId = value;
    }

    /**
     * Gets the value of the productName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the difference property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getDifference() {
        return difference;
    }

    /**
     * Sets the value of the difference property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setDifference(Integer value) {
        this.difference = value;
    }

}
