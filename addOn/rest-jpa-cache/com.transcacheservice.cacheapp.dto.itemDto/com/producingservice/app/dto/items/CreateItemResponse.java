//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.26 at 02:00:07 AM YEKT 
//


package com.producingservice.app.dto.items;

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
 *         &lt;element name="createdItem" type="{http://com/producingservice/app/dto/items}itemDto"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "createdItem"
})
@XmlRootElement(name = "createItemResponse")
public class CreateItemResponse {

    @XmlElement(required = true)
    protected ItemDto createdItem;

    /**
     * Gets the value of the createdItem property.
     *
     * @return possible object is
     * {@link ItemDto }
     */
    public ItemDto getCreatedItem() {
        return createdItem;
    }

    /**
     * Sets the value of the createdItem property.
     *
     * @param value allowed object is
     *              {@link ItemDto }
     */
    public void setCreatedItem(ItemDto value) {
        this.createdItem = value;
    }

}
