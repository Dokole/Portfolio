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
 *         &lt;element name="updatedManufacturer" type="{http://com.producingservice.app.dto.database}manufacturerDto"/&gt;
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
    "updatedManufacturer"
})
@XmlRootElement(name = "updateManufacturerResponse")
public class UpdateManufacturerResponse {

    @XmlElement(required = true)
    protected ManufacturerDto updatedManufacturer;

    /**
     * Gets the value of the updatedManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link ManufacturerDto }
     *     
     */
    public ManufacturerDto getUpdatedManufacturer() {
        return updatedManufacturer;
    }

    /**
     * Sets the value of the updatedManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManufacturerDto }
     *     
     */
    public void setUpdatedManufacturer(ManufacturerDto value) {
        this.updatedManufacturer = value;
    }

}
