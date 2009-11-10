//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package rpg.v4.middleware.jaxb;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="Association" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Level" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Attack"/>
 *               &lt;enumeration value="Utility"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SubType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="At-Will"/>
 *               &lt;enumeration value="Encounter"/>
 *               &lt;enumeration value="Daily"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Source">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Arcane"/>
 *               &lt;enumeration value="Divine"/>
 *               &lt;enumeration value="Marital"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AttackType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Melee"/>
 *               &lt;enumeration value="Ranged"/>
 *               &lt;enumeration value="Close"/>
 *               &lt;enumeration value="Area"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DamageType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Acid"/>
 *               &lt;enumeration value="Cold"/>
 *               &lt;enumeration value="Fire"/>
 *               &lt;enumeration value="Force"/>
 *               &lt;enumeration value="Lightning"/>
 *               &lt;enumeration value="Necrotic"/>
 *               &lt;enumeration value="Poison"/>
 *               &lt;enumeration value="Psychic"/>
 *               &lt;enumeration value="Radiant"/>
 *               &lt;enumeration value="Thunder"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="EffectType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Charm"/>
 *               &lt;enumeration value="Conjuration"/>
 *               &lt;enumeration value="Fear"/>
 *               &lt;enumeration value="Healing"/>
 *               &lt;enumeration value="Illusion"/>
 *               &lt;enumeration value="Poison"/>
 *               &lt;enumeration value="Polymorph"/>
 *               &lt;enumeration value="Reliable"/>
 *               &lt;enumeration value="Sleep"/>
 *               &lt;enumeration value="Stance"/>
 *               &lt;enumeration value="Teleportation"/>
 *               &lt;enumeration value="Zone"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ActionType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Standard"/>
 *               &lt;enumeration value="Move"/>
 *               &lt;enumeration value="Immediate interrupt"/>
 *               &lt;enumeration value="Immediate reaction"/>
 *               &lt;enumeration value="Minor"/>
 *               &lt;enumeration value="Free"/>
 *               &lt;enumeration value="None"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Accessory">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Implement"/>
 *               &lt;enumeration value="Weapon"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Range">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Melee"/>
 *               &lt;enumeration value="Ranged"/>
 *               &lt;enumeration value="Close"/>
 *               &lt;enumeration value="Area"/>
 *               &lt;enumeration value="Personal"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Attack">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AttackerAbility" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DefenderAbility" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DamageNumDice" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="DamageDiceType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "association",
    "level",
    "type",
    "subType",
    "source",
    "attackType",
    "damageType",
    "effectType",
    "actionType",
    "accessory",
    "range",
    "attack"
})
@XmlRootElement(name = "V4_Power")
public class V4Power {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Association", required = true)
    protected String association;
    @XmlElement(name = "Level")
    protected int level;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "SubType", required = true)
    protected String subType;
    @XmlElement(name = "Source", required = true)
    protected String source;
    @XmlElement(name = "AttackType", required = true)
    protected String attackType;
    @XmlElement(name = "DamageType", required = true)
    protected String damageType;
    @XmlElement(name = "EffectType", required = true)
    protected String effectType;
    @XmlElement(name = "ActionType", required = true)
    protected String actionType;
    @XmlElement(name = "Accessory", required = true)
    protected String accessory;
    @XmlElement(name = "Range", required = true)
    protected String range;
    @XmlElement(name = "Attack", required = true)
    protected V4Power.Attack attack;

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
     * Gets the value of the association property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociation() {
        return association;
    }

    /**
     * Sets the value of the association property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociation(String value) {
        this.association = value;
    }

    /**
     * Gets the value of the level property.
     * 
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     */
    public void setLevel(int value) {
        this.level = value;
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
     * Gets the value of the subType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubType() {
        return subType;
    }

    /**
     * Sets the value of the subType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubType(String value) {
        this.subType = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the attackType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttackType() {
        return attackType;
    }

    /**
     * Sets the value of the attackType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttackType(String value) {
        this.attackType = value;
    }

    /**
     * Gets the value of the damageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDamageType() {
        return damageType;
    }

    /**
     * Sets the value of the damageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDamageType(String value) {
        this.damageType = value;
    }

    /**
     * Gets the value of the effectType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectType() {
        return effectType;
    }

    /**
     * Sets the value of the effectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectType(String value) {
        this.effectType = value;
    }

    /**
     * Gets the value of the actionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Sets the value of the actionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionType(String value) {
        this.actionType = value;
    }

    /**
     * Gets the value of the accessory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessory() {
        return accessory;
    }

    /**
     * Sets the value of the accessory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessory(String value) {
        this.accessory = value;
    }

    /**
     * Gets the value of the range property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRange() {
        return range;
    }

    /**
     * Sets the value of the range property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRange(String value) {
        this.range = value;
    }

    /**
     * Gets the value of the attack property.
     * 
     * @return
     *     possible object is
     *     {@link V4Power.Attack }
     *     
     */
    public V4Power.Attack getAttack() {
        return attack;
    }

    /**
     * Sets the value of the attack property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4Power.Attack }
     *     
     */
    public void setAttack(V4Power.Attack value) {
        this.attack = value;
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
     *         &lt;element name="AttackerAbility" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DefenderAbility" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DamageNumDice" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="DamageDiceType" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        "attackerAbility",
        "defenderAbility",
        "damageNumDice",
        "damageDiceType"
    })
    public static class Attack {

        @XmlElement(name = "AttackerAbility", required = true)
        protected String attackerAbility;
        @XmlElement(name = "DefenderAbility", required = true)
        protected String defenderAbility;
        @XmlElement(name = "DamageNumDice")
        protected int damageNumDice;
        @XmlElement(name = "DamageDiceType")
        protected int damageDiceType;

        /**
         * Gets the value of the attackerAbility property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAttackerAbility() {
            return attackerAbility;
        }

        /**
         * Sets the value of the attackerAbility property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAttackerAbility(String value) {
            this.attackerAbility = value;
        }

        /**
         * Gets the value of the defenderAbility property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDefenderAbility() {
            return defenderAbility;
        }

        /**
         * Sets the value of the defenderAbility property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDefenderAbility(String value) {
            this.defenderAbility = value;
        }

        /**
         * Gets the value of the damageNumDice property.
         * 
         */
        public int getDamageNumDice() {
            return damageNumDice;
        }

        /**
         * Sets the value of the damageNumDice property.
         * 
         */
        public void setDamageNumDice(int value) {
            this.damageNumDice = value;
        }

        /**
         * Gets the value of the damageDiceType property.
         * 
         */
        public int getDamageDiceType() {
            return damageDiceType;
        }

        /**
         * Sets the value of the damageDiceType property.
         * 
         */
        public void setDamageDiceType(int value) {
            this.damageDiceType = value;
        }

    }

}