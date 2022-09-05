//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2013.07.25 a las 05:49:57 PM CEST 
//


package org.tempuri.po;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para PurchaseOrderType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PurchaseOrderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shipTo" type="{http://tempuri.org/po.xsd}USAddress"/>
 *         &lt;element name="billTo" type="{http://tempuri.org/po.xsd}USAddress"/>
 *         &lt;element ref="{http://tempuri.org/po.xsd}comment" minOccurs="0"/>
 *         &lt;element name="items" type="{http://tempuri.org/po.xsd}Items"/>
 *       &lt;/sequence>
 *       &lt;attribute name="orderDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurchaseOrderType", propOrder = {
    "shipTo",
    "billTo",
    "comment",
    "items"
})
public class PurchaseOrderType {

    @XmlElement(required = true)
    protected USAddress shipTo;
    @XmlElement(required = true)
    protected USAddress billTo;
    protected String comment;
    @XmlElement(required = true)
    protected Items items;
    @XmlAttribute(name = "orderDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar orderDate;

    /**
     * Obtiene el valor de la propiedad shipTo.
     * 
     * @return
     *     possible object is
     *     {@link USAddress }
     *     
     */
    public USAddress getShipTo() {
        return shipTo;
    }

    /**
     * Define el valor de la propiedad shipTo.
     * 
     * @param value
     *     allowed object is
     *     {@link USAddress }
     *     
     */
    public void setShipTo(USAddress value) {
        this.shipTo = value;
    }

    /**
     * Obtiene el valor de la propiedad billTo.
     * 
     * @return
     *     possible object is
     *     {@link USAddress }
     *     
     */
    public USAddress getBillTo() {
        return billTo;
    }

    /**
     * Define el valor de la propiedad billTo.
     * 
     * @param value
     *     allowed object is
     *     {@link USAddress }
     *     
     */
    public void setBillTo(USAddress value) {
        this.billTo = value;
    }

    /**
     * Obtiene el valor de la propiedad comment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Define el valor de la propiedad comment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Obtiene el valor de la propiedad items.
     * 
     * @return
     *     possible object is
     *     {@link Items }
     *     
     */
    public Items getItems() {
        return items;
    }

    /**
     * Define el valor de la propiedad items.
     * 
     * @param value
     *     allowed object is
     *     {@link Items }
     *     
     */
    public void setItems(Items value) {
        this.items = value;
    }

    /**
     * Obtiene el valor de la propiedad orderDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderDate() {
        return orderDate;
    }

    /**
     * Define el valor de la propiedad orderDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderDate(XMLGregorianCalendar value) {
        this.orderDate = value;
    }

}
