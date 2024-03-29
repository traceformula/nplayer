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
 *         &lt;element ref="{rgb.jaxb.character}V4_StateID" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Max"/>
 *             &lt;enumeration value="Min"/>
 *             &lt;enumeration value="Half"/>
 *             &lt;enumeration value="Contains"/>
 *             &lt;enumeration value="GreaterThan"/>
 *             &lt;enumeration value="LesserThan"/>
 *             &lt;enumeration value="Equals"/>
 *             &lt;enumeration value="AbilityModifier"/>
 *             &lt;enumeration value="HpPrLvlMultiplier"/>
 *             &lt;enumeration value="DeduceLevel"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="StringToContain" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "v4StateID"
})
@XmlRootElement(name = "V4_Function")
public class V4Function implements Serializable {

    @XmlElement(name = "V4_StateID")
    protected List<String> v4StateID;
    @XmlAttribute(name = "Type", required = true)
    protected String type;
    @XmlAttribute(name = "StringToContain")
    protected String stringToContain;

    /**
     * Gets the value of the v4StateID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the v4StateID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getV4StateID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getV4StateID() {
        if (v4StateID == null) {
            v4StateID = new ArrayList<String>();
        }
        return this.v4StateID;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the stringToContain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringToContain() {
        return stringToContain;
    }

    /**
     * Sets the value of the stringToContain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringToContain(String value) {
        this.stringToContain = value;
    }

}
