//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.13 at 06:05:43 PM YEKT 
//


package database.dto.app.producingservice.com;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="createdCategory" type="{http://com.producingservice.app.dto.database}categoryDto"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "createdCategory"
})
@XmlRootElement(name = "createCategoryResponse")
public class CreateCategoryResponse {

    @XmlElement(required = true)
    protected CategoryDto createdCategory;

    /**
     * Gets the value of the createdCategory property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryDto }
     *     
     */
    public CategoryDto getCreatedCategory() {
        return createdCategory;
    }

    /**
     * Sets the value of the createdCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryDto }
     *     
     */
    public void setCreatedCategory(CategoryDto value) {
        this.createdCategory = value;
    }

}
