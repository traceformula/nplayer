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
 *         &lt;element name="Modifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ModifierClass" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="String"/>
 *               &lt;pattern value="Integer"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SourceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{rgb.jaxb.character}V4_ModifierType"/>
 *         &lt;element name="TargetID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DiceModifier" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DiceType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="NumberOfDice" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element ref="{rgb.jaxb.character}V4_StateID"/>
 *                   &lt;element name="OverallMultiplier" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DisalowDiceRow" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "modifier",
    "modifierClass",
    "sourceID",
    "v4ModifierType",
    "targetID",
    "diceModifier",
    "disalowDiceRow"
})
@XmlRootElement(name = "V4_Modifier")
public class V4Modifier implements Serializable {

    @XmlElement(name = "Modifier", required = true)
    protected String modifier;
    @XmlElement(name = "ModifierClass")
    protected String modifierClass;
    @XmlElement(name = "SourceID", required = true)
    protected String sourceID;
    @XmlElement(name = "V4_ModifierType", required = true)
    protected String v4ModifierType;
    @XmlElement(name = "TargetID", required = true)
    protected String targetID;
    @XmlElement(name = "DiceModifier")
    protected V4Modifier.DiceModifier diceModifier;
    @XmlElement(name = "DisalowDiceRow")
    protected Boolean disalowDiceRow;

    /**
     * Gets the value of the modifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Object getModifier() {

        if (modifierClass == null || modifierClass.equals("Integer"))
        {
            Integer returnValue = Integer.parseInt(modifier);
            return returnValue;
        }

        return modifier;
    }

    /**
     * Sets the value of the modifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifier(Object value) {
        this.modifier = value.toString();
    }

    /**
     * Gets the value of the modifierClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifierClass() {
        return modifierClass;
    }

    /**
     * Sets the value of the modifierClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifierClass(String value) {
        this.modifierClass = value;
    }

    /**
     * Gets the value of the sourceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceID() {
        return sourceID;
    }

    /**
     * Sets the value of the sourceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceID(String value) {
        this.sourceID = value;
    }

    /**
     * Gets the value of the v4ModifierType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getV4ModifierType() {
        return v4ModifierType;
    }

    /**
     * Sets the value of the v4ModifierType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setV4ModifierType(String value) {
        this.v4ModifierType = value;
    }

    /**
     * Gets the value of the targetID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetID() {
        return targetID;
    }

    /**
     * Sets the value of the targetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetID(String value) {
        this.targetID = value;
    }

    /**
     * Gets the value of the diceModifier property.
     * 
     * @return
     *     possible object is
     *     {@link V4Modifier.DiceModifier }
     *     
     */
    public V4Modifier.DiceModifier getDiceModifier() {
        return diceModifier;
    }

    /**
     * Sets the value of the diceModifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4Modifier.DiceModifier }
     *     
     */
    public void setDiceModifier(V4Modifier.DiceModifier value) {
        this.diceModifier = value;
    }

    /**
     * Gets the value of the disalowDiceRow property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisalowDiceRow() {
        return disalowDiceRow;
    }

    /**
     * Sets the value of the disalowDiceRow property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisalowDiceRow(Boolean value) {
        this.disalowDiceRow = value;
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
     *       &lt;sequence>
     *         &lt;element name="DiceType" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="NumberOfDice" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element ref="{rgb.jaxb.character}V4_StateID"/>
     *         &lt;element name="OverallMultiplier" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
        "diceType",
        "numberOfDice",
        "v4StateID",
        "overallMultiplier"
    })
    public static class DiceModifier implements Serializable {

        @XmlElement(name = "DiceType")
        protected int diceType;
        @XmlElement(name = "NumberOfDice")
        protected int numberOfDice;
        @XmlElement(name = "V4_StateID", required = true)
        protected String v4StateID;
        @XmlElement(name = "OverallMultiplier", defaultValue = "1")
        protected float overallMultiplier;

        /**
         * Gets the value of the diceType property.
         * 
         */
        public int getDiceType() {
            return diceType;
        }

        /**
         * Sets the value of the diceType property.
         * 
         */
        public void setDiceType(int value) {
            this.diceType = value;
        }

        /**
         * Gets the value of the numberOfDice property.
         * 
         */
        public int getNumberOfDice() {
            return numberOfDice;
        }

        /**
         * Sets the value of the numberOfDice property.
         * 
         */
        public void setNumberOfDice(int value) {
            this.numberOfDice = value;
        }

        /**
         * Gets the value of the v4StateID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getV4StateID() {
            return v4StateID;
        }

        /**
         * Sets the value of the v4StateID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setV4StateID(String value) {
            this.v4StateID = value;
        }

        /**
         * Gets the value of the overallMultiplier property.
         * 
         */
        public float getOverallMultiplier() {
            return overallMultiplier;
        }

        /**
         * Sets the value of the overallMultiplier property.
         * 
         */
        public void setOverallMultiplier(float value) {
            this.overallMultiplier = value;
        }

    }

}
