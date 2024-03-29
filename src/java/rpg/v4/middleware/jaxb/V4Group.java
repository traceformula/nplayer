//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package rpg.v4.middleware.jaxb;

import javax.xml.bind.annotation.*;
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
 *         &lt;element name="InBattle" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Color">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="Red" type="{http://www.w3.org/2001/XMLSchema}int" default="255" />
 *                 &lt;attribute name="Green" type="{http://www.w3.org/2001/XMLSchema}int" default="255" />
 *                 &lt;attribute name="Blue" type="{http://www.w3.org/2001/XMLSchema}int" default="255" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{rgb.jaxb.character}V4_EntityNameKeyPair" maxOccurs="unbounded" minOccurs="0"/>
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
    "inBattle",
    "color",
    "v4EntityNameKeyPair"
})
@XmlRootElement(name = "V4_Group")
public class V4Group {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "InBattle")
    protected boolean inBattle;
    @XmlElement(name = "Color", required = true)
    protected V4Group.Color color;
    @XmlElement(name = "V4_EntityNameKeyPair")
    protected List<V4EntityNameKeyPair> v4EntityNameKeyPair;

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
     * Gets the value of the inBattle property.
     * 
     */
    public boolean isInBattle() {
        return inBattle;
    }

    /**
     * Sets the value of the inBattle property.
     * 
     */
    public void setInBattle(boolean value) {
        this.inBattle = value;
    }

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link V4Group.Color }
     *     
     */
    public V4Group.Color getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4Group.Color }
     *     
     */
    public void setColor(V4Group.Color value) {
        this.color = value;
    }

    /**
     * Gets the value of the v4EntityNameKeyPair property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the v4EntityNameKeyPair property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getV4EntityNameKeyPair().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link V4EntityNameKeyPair }
     * 
     * 
     */
    public List<V4EntityNameKeyPair> getV4EntityNameKeyPair() {
        if (v4EntityNameKeyPair == null) {
            v4EntityNameKeyPair = new ArrayList<V4EntityNameKeyPair>();
        }
        return this.v4EntityNameKeyPair;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="Red" type="{http://www.w3.org/2001/XMLSchema}int" default="255" />
     *       &lt;attribute name="Green" type="{http://www.w3.org/2001/XMLSchema}int" default="255" />
     *       &lt;attribute name="Blue" type="{http://www.w3.org/2001/XMLSchema}int" default="255" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Color {

        @XmlAttribute(name = "Red")
        protected Integer red;
        @XmlAttribute(name = "Green")
        protected Integer green;
        @XmlAttribute(name = "Blue")
        protected Integer blue;

        /**
         * Gets the value of the red property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public int getRed() {
            if (red == null) {
                return  255;
            } else {
                return red;
            }
        }

        /**
         * Sets the value of the red property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setRed(Integer value) {
            this.red = value;
        }

        /**
         * Gets the value of the green property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public int getGreen() {
            if (green == null) {
                return  255;
            } else {
                return green;
            }
        }

        /**
         * Sets the value of the green property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setGreen(Integer value) {
            this.green = value;
        }

        /**
         * Gets the value of the blue property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public int getBlue() {
            if (blue == null) {
                return  255;
            } else {
                return blue;
            }
        }

        /**
         * Sets the value of the blue property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setBlue(Integer value) {
            this.blue = value;
        }

    }

}
