//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2013.07.25 a las 05:49:57 PM CEST 
//


package org.tempuri.po;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri.po package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PurchaseOrder_QNAME = new QName("http://tempuri.org/po.xsd", "purchaseOrder");
    private final static QName _Comment_QNAME = new QName("http://tempuri.org/po.xsd", "comment");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri.po
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Items }
     * 
     */
    public Items createItems() {
        return new Items();
    }

    /**
     * Create an instance of {@link PurchaseOrderType }
     * 
     */
    public PurchaseOrderType createPurchaseOrderType() {
        return new PurchaseOrderType();
    }

    /**
     * Create an instance of {@link USAddress }
     * 
     */
    public USAddress createUSAddress() {
        return new USAddress();
    }

    /**
     * Create an instance of {@link Items.Item }
     * 
     */
    public Items.Item createItemsItem() {
        return new Items.Item();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PurchaseOrderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/po.xsd", name = "purchaseOrder")
    public JAXBElement<PurchaseOrderType> createPurchaseOrder(PurchaseOrderType value) {
        return new JAXBElement<PurchaseOrderType>(_PurchaseOrder_QNAME, PurchaseOrderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/po.xsd", name = "comment")
    public JAXBElement<String> createComment(String value) {
        return new JAXBElement<String>(_Comment_QNAME, String.class, null, value);
    }

}
