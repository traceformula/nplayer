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
 *         &lt;element name="BaseWeapon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProficiencyBonus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Damage">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="NumDice" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="DiceType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RangeIncrement" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Group">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Axe"/>
 *               &lt;enumeration value="Bow"/>
 *               &lt;enumeration value="Crossbow"/>
 *               &lt;enumeration value="Flail"/>
 *               &lt;enumeration value="Hammer"/>
 *               &lt;enumeration value="Heavy Blade"/>
 *               &lt;enumeration value="Light Blade"/>
 *               &lt;enumeration value="Mace"/>
 *               &lt;enumeration value="Pick"/>
 *               &lt;enumeration value="Polearm"/>
 *               &lt;enumeration value="Sling"/>
 *               &lt;enumeration value="Spear"/>
 *               &lt;enumeration value="Staff"/>
 *               &lt;enumeration value="Unarmed"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Melee"/>
 *               &lt;enumeration value="Ranged"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Category">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Simple"/>
 *               &lt;enumeration value="Military"/>
 *               &lt;enumeration value="Superior"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="HandRequirement" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Properties">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="HeavyThrown" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="HighCrit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="LightThrown" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="Load" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="Off-Hand" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="Reach" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="Small" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="Versatile" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "baseWeapon",
    "proficiencyBonus",
    "damage",
    "rangeIncrement",
    "price",
    "weight",
    "group",
    "type",
    "category",
    "handRequirement",
    "properties",
    "v4ModifierList"
})
@XmlRootElement(name = "V4_Weapon")
public class V4Weapon implements Serializable {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "BaseWeapon")
    protected String baseWeapon;
    @XmlElement(name = "ProficiencyBonus")
    protected int proficiencyBonus;
    @XmlElement(name = "Damage", required = true)
    protected V4Weapon.Damage damage;
    @XmlElement(name = "RangeIncrement")
    protected Integer rangeIncrement;
    @XmlElement(name = "Price")
    protected int price;
    @XmlElement(name = "Weight")
    protected double weight;
    @XmlElement(name = "Group", required = true)
    protected String group;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Category", required = true)
    protected String category;
    @XmlElement(name = "HandRequirement")
    protected int handRequirement;
    @XmlElement(name = "Properties", required = true)
    protected V4Weapon.Properties properties;
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
     * Gets the value of the baseWeapon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseWeapon() {
        return baseWeapon;
    }

    /**
     * Sets the value of the baseWeapon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseWeapon(String value) {
        this.baseWeapon = value;
    }

    /**
     * Gets the value of the proficiencyBonus property.
     * 
     */
    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    /**
     * Sets the value of the proficiencyBonus property.
     * 
     */
    public void setProficiencyBonus(int value) {
        this.proficiencyBonus = value;
    }

    /**
     * Gets the value of the damage property.
     * 
     * @return
     *     possible object is
     *     {@link V4Weapon.Damage }
     *     
     */
    public V4Weapon.Damage getDamage() {
        return damage;
    }

    /**
     * Sets the value of the damage property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4Weapon.Damage }
     *     
     */
    public void setDamage(V4Weapon.Damage value) {
        this.damage = value;
    }

    /**
     * Gets the value of the rangeIncrement property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRangeIncrement() {
        return rangeIncrement;
    }

    /**
     * Sets the value of the rangeIncrement property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRangeIncrement(Integer value) {
        this.rangeIncrement = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(int value) {
        this.price = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     */
    public void setWeight(double value) {
        this.weight = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroup(String value) {
        this.group = value;
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
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the handRequirement property.
     * 
     */
    public int getHandRequirement() {
        return handRequirement;
    }

    /**
     * Sets the value of the handRequirement property.
     * 
     */
    public void setHandRequirement(int value) {
        this.handRequirement = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link V4Weapon.Properties }
     *     
     */
    public V4Weapon.Properties getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4Weapon.Properties }
     *     
     */
    public void setProperties(V4Weapon.Properties value) {
        this.properties = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="NumDice" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="DiceType" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        "numDice",
        "diceType"
    })
    public static class Damage implements Serializable {

        @XmlElement(name = "NumDice")
        protected int numDice;
        @XmlElement(name = "DiceType")
        protected int diceType;

        /**
         * Gets the value of the numDice property.
         * 
         */
        public int getNumDice() {
            return numDice;
        }

        /**
         * Sets the value of the numDice property.
         * 
         */
        public void setNumDice(int value) {
            this.numDice = value;
        }

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
     *         &lt;element name="HeavyThrown" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="HighCrit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="LightThrown" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="Load" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="Off-Hand" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="Reach" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="Small" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="Versatile" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
        "heavyThrown",
        "highCrit",
        "lightThrown",
        "load",
        "offHand",
        "reach",
        "small",
        "versatile"
    })
    public static class Properties implements Serializable {

        @XmlElement(name = "HeavyThrown")
        protected boolean heavyThrown;
        @XmlElement(name = "HighCrit")
        protected boolean highCrit;
        @XmlElement(name = "LightThrown")
        protected boolean lightThrown;
        @XmlElement(name = "Load")
        protected boolean load;
        @XmlElement(name = "Off-Hand")
        protected boolean offHand;
        @XmlElement(name = "Reach")
        protected boolean reach;
        @XmlElement(name = "Small")
        protected boolean small;
        @XmlElement(name = "Versatile")
        protected boolean versatile;

        /**
         * Gets the value of the heavyThrown property.
         * 
         */
        public boolean isHeavyThrown() {
            return heavyThrown;
        }

        /**
         * Sets the value of the heavyThrown property.
         * 
         */
        public void setHeavyThrown(boolean value) {
            this.heavyThrown = value;
        }

        /**
         * Gets the value of the highCrit property.
         * 
         */
        public boolean isHighCrit() {
            return highCrit;
        }

        /**
         * Sets the value of the highCrit property.
         * 
         */
        public void setHighCrit(boolean value) {
            this.highCrit = value;
        }

        /**
         * Gets the value of the lightThrown property.
         * 
         */
        public boolean isLightThrown() {
            return lightThrown;
        }

        /**
         * Sets the value of the lightThrown property.
         * 
         */
        public void setLightThrown(boolean value) {
            this.lightThrown = value;
        }

        /**
         * Gets the value of the load property.
         * 
         */
        public boolean isLoad() {
            return load;
        }

        /**
         * Sets the value of the load property.
         * 
         */
        public void setLoad(boolean value) {
            this.load = value;
        }

        /**
         * Gets the value of the offHand property.
         * 
         */
        public boolean isOffHand() {
            return offHand;
        }

        /**
         * Sets the value of the offHand property.
         * 
         */
        public void setOffHand(boolean value) {
            this.offHand = value;
        }

        /**
         * Gets the value of the reach property.
         * 
         */
        public boolean isReach() {
            return reach;
        }

        /**
         * Sets the value of the reach property.
         * 
         */
        public void setReach(boolean value) {
            this.reach = value;
        }

        /**
         * Gets the value of the small property.
         * 
         */
        public boolean isSmall() {
            return small;
        }

        /**
         * Sets the value of the small property.
         * 
         */
        public void setSmall(boolean value) {
            this.small = value;
        }

        /**
         * Gets the value of the versatile property.
         * 
         */
        public boolean isVersatile() {
            return versatile;
        }

        /**
         * Sets the value of the versatile property.
         * 
         */
        public void setVersatile(boolean value) {
            this.versatile = value;
        }

    }

}
