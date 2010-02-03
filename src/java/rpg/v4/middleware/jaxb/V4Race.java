//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package rpg.v4.middleware.jaxb;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element ref="{rgb.jaxb.character}V4_ModifierList"/>
 *         &lt;element ref="{rgb.jaxb.character}V4_BodyPart" maxOccurs="unbounded"/>
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
    "v4ModifierList",
    "v4BodyPart"
})
@XmlRootElement(name = "V4_Race")
public class V4Race implements Serializable {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "V4_ModifierList", required = true)
    protected V4ModifierList v4ModifierList;
    @XmlElement(name = "V4_BodyPart", required = true)
    protected List<V4BodyPart> v4BodyPart;

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

    /**
     * Gets the value of the v4BodyPart property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the v4BodyPart property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getV4BodyPart().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link V4BodyPart }
     * 
     * 
     */
    public List<V4BodyPart> getV4BodyPart() {
        if (v4BodyPart == null) {
            v4BodyPart = new ArrayList<V4BodyPart>();
        }
        return this.v4BodyPart;
    }

}