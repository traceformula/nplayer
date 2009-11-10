//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package rpg.v4.middleware.jaxb;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ParagonPath" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{rgb.jaxb.character}V4_ModifierList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "paragonPath",
    "v4ModifierList"
})
@XmlRootElement(name = "V4_Class")
public class V4Class implements Serializable {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ParagonPath")
    protected boolean paragonPath;
    @XmlElement(name = "V4_ModifierList", required = true)
    protected V4ModifierList v4ModifierList;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the paragonPath property.
     * 
     */
    public boolean isParagonPath() {
        return paragonPath;
    }

    /**
     * Sets the value of the paragonPath property.
     * 
     */
    public void setParagonPath(boolean value) {
        this.paragonPath = value;
    }

    /**
     * Gets the value of the v4ModifierList property.
     * 
     * @return
     *     possible object is
     *     {@link V4ModifierList }
     *     
     */
    public V4ModifierList getV4ModifierList() {
        return v4ModifierList;
    }

    /**
     * Sets the value of the v4ModifierList property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4ModifierList }
     *     
     */
    public void setV4ModifierList(V4ModifierList value) {
        this.v4ModifierList = value;
    }

}
