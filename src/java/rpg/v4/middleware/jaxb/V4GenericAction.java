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
 *         &lt;element name="Requirements">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CharacterClass" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="Level" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Power"/>
 *               &lt;enumeration value="Action"/>
 *               &lt;enumeration value="Feat"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SubType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="At will"/>
 *               &lt;enumeration value="Encounter"/>
 *               &lt;enumeration value="Daily"/>
 *               &lt;enumeration value="Utility"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Source">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Arcane"/>
 *               &lt;enumeration value="Divine"/>
 *               &lt;enumeration value="Martial"/>
 *               &lt;enumeration value="Nature"/>
 *               &lt;enumeration value="Ki"/>
 *               &lt;enumeration value="Psionic"/>
 *               &lt;enumeration value="Elemental"/>
 *               &lt;enumeration value="Primal"/>
 *               &lt;enumeration value="Shadow"/>
 *               &lt;enumeration value="n/a"/>
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
 *               &lt;enumeration value="Psionic"/>
 *               &lt;enumeration value="Psychic"/>
 *               &lt;enumeration value="Radiant"/>
 *               &lt;enumeration value="Thunder"/>
 *               &lt;enumeration value="n/a"/>
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
 *               &lt;enumeration value="Posion"/>
 *               &lt;enumeration value="Polymorph"/>
 *               &lt;enumeration value="Reliable"/>
 *               &lt;enumeration value="Sleep"/>
 *               &lt;enumeration value="Stance"/>
 *               &lt;enumeration value="Teleportation"/>
 *               &lt;enumeration value="Zone"/>
 *               &lt;enumeration value="n/a"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Implement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActionType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Standard"/>
 *               &lt;enumeration value="Move"/>
 *               &lt;enumeration value="Immediate interrupt"/>
 *               &lt;enumeration value="Immediate reaction"/>
 *               &lt;enumeration value="Free"/>
 *               &lt;enumeration value=""/>
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
 *               &lt;enumeration value="Personal"/>
 *               &lt;enumeration value="n/a"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SecondaryAttackType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Melee"/>
 *               &lt;enumeration value="Ranged"/>
 *               &lt;enumeration value="Close"/>
 *               &lt;enumeration value="Area"/>
 *               &lt;enumeration value="Personal"/>
 *               &lt;enumeration value="n/a"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Target">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="You"/>
 *               &lt;enumeration value="You or one ally"/>
 *               &lt;enumeration value="One creature"/>
 *               &lt;enumeration value="One enemy"/>
 *               &lt;enumeration value="One object"/>
 *               &lt;enumeration value="Multiple enemies"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Attack">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AttackerStateID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DefenderStateID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DisalowDiceRow" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="HitModifiers">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{rgb.jaxb.character}V4_Modifier" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MissModifiers">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{rgb.jaxb.character}V4_Modifier" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{rgb.jaxb.character}V4_EntityNameKeyPair" minOccurs="0"/>
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
    "requirements",
    "type",
    "subType",
    "source",
    "damageType",
    "effectType",
    "implement",
    "actionType",
    "attackType",
    "secondaryAttackType",
    "target",
    "attack",
    "hitModifiers",
    "missModifiers",
    "v4EntityNameKeyPair"
})
@XmlRootElement(name = "V4_GenericAction")
public class V4GenericAction implements Serializable {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Requirements", required = true)
    protected V4GenericAction.Requirements requirements;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "SubType", required = true)
    protected String subType;
    @XmlElement(name = "Source", required = true)
    protected String source;
    @XmlElement(name = "DamageType", required = true)
    protected String damageType;
    @XmlElement(name = "EffectType", required = true)
    protected String effectType;
    @XmlElement(name = "Implement", required = true)
    protected String implement;
    @XmlElement(name = "ActionType", required = true)
    protected String actionType;
    @XmlElement(name = "AttackType", required = true)
    protected String attackType;
    @XmlElement(name = "SecondaryAttackType", required = true)
    protected String secondaryAttackType;
    @XmlElement(name = "Target", required = true)
    protected String target;
    @XmlElement(name = "Attack", required = true)
    protected V4GenericAction.Attack attack;
    @XmlElement(name = "HitModifiers", required = true)
    protected V4GenericAction.HitModifiers hitModifiers;
    @XmlElement(name = "MissModifiers", required = true)
    protected V4GenericAction.MissModifiers missModifiers;
    @XmlElement(name = "V4_EntityNameKeyPair")
    protected V4EntityNameKeyPair v4EntityNameKeyPair;

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
     * Gets the value of the requirements property.
     * 
     * @return
     *     possible object is
     *     {@link V4GenericAction.Requirements }
     *     
     */
    public V4GenericAction.Requirements getRequirements() {
        return requirements;
    }

    /**
     * Sets the value of the requirements property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4GenericAction.Requirements }
     *     
     */
    public void setRequirements(V4GenericAction.Requirements value) {
        this.requirements = value;
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
     * Gets the value of the implement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImplement() {
        return implement;
    }

    /**
     * Sets the value of the implement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImplement(String value) {
        this.implement = value;
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
     * Gets the value of the secondaryAttackType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryAttackType() {
        return secondaryAttackType;
    }

    /**
     * Sets the value of the secondaryAttackType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryAttackType(String value) {
        this.secondaryAttackType = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

    /**
     * Gets the value of the attack property.
     * 
     * @return
     *     possible object is
     *     {@link V4GenericAction.Attack }
     *     
     */
    public V4GenericAction.Attack getAttack() {
        return attack;
    }

    /**
     * Sets the value of the attack property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4GenericAction.Attack }
     *     
     */
    public void setAttack(V4GenericAction.Attack value) {
        this.attack = value;
    }

    /**
     * Gets the value of the hitModifiers property.
     * 
     * @return
     *     possible object is
     *     {@link V4GenericAction.HitModifiers }
     *     
     */
    public V4GenericAction.HitModifiers getHitModifiers() {
        return hitModifiers;
    }

    /**
     * Sets the value of the hitModifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4GenericAction.HitModifiers }
     *     
     */
    public void setHitModifiers(V4GenericAction.HitModifiers value) {
        this.hitModifiers = value;
    }

    /**
     * Gets the value of the missModifiers property.
     * 
     * @return
     *     possible object is
     *     {@link V4GenericAction.MissModifiers }
     *     
     */
    public V4GenericAction.MissModifiers getMissModifiers() {
        return missModifiers;
    }

    /**
     * Sets the value of the missModifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4GenericAction.MissModifiers }
     *     
     */
    public void setMissModifiers(V4GenericAction.MissModifiers value) {
        this.missModifiers = value;
    }

    /**
     * Gets the value of the v4EntityNameKeyPair property.
     * 
     * @return
     *     possible object is
     *     {@link V4EntityNameKeyPair }
     *     
     */
    public V4EntityNameKeyPair getV4EntityNameKeyPair() {
        return v4EntityNameKeyPair;
    }

    /**
     * Sets the value of the v4EntityNameKeyPair property.
     * 
     * @param value
     *     allowed object is
     *     {@link V4EntityNameKeyPair }
     *     
     */
    public void setV4EntityNameKeyPair(V4EntityNameKeyPair value) {
        this.v4EntityNameKeyPair = value;
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
     *         &lt;element name="AttackerStateID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DefenderStateID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "attackerStateID",
        "defenderStateID",
        "disalowDiceRow"
    })
    public static class Attack implements Serializable {

        @XmlElement(name = "AttackerStateID")
        protected String attackerStateID;
        @XmlElement(name = "DefenderStateID")
        protected String defenderStateID;
        @XmlElement(name = "DisalowDiceRow")
        protected Boolean disalowDiceRow;

        /**
         * Gets the value of the attackerStateID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAttackerStateID() {
            return attackerStateID;
        }

        /**
         * Sets the value of the attackerStateID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAttackerStateID(String value) {
            this.attackerStateID = value;
        }

        /**
         * Gets the value of the defenderStateID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDefenderStateID() {
            return defenderStateID;
        }

        /**
         * Sets the value of the defenderStateID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDefenderStateID(String value) {
            this.defenderStateID = value;
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
     *         &lt;element ref="{rgb.jaxb.character}V4_Modifier" maxOccurs="unbounded" minOccurs="0"/>
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
        "v4Modifier"
    })
    public static class HitModifiers implements Serializable {

        @XmlElement(name = "V4_Modifier")
        protected List<V4Modifier> v4Modifier;

        /**
         * Gets the value of the v4Modifier property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the v4Modifier property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getV4Modifier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link V4Modifier }
         * 
         * 
         */
        public List<V4Modifier> getV4Modifier() {
            if (v4Modifier == null) {
                v4Modifier = new ArrayList<V4Modifier>();
            }
            return this.v4Modifier;
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
     *         &lt;element ref="{rgb.jaxb.character}V4_Modifier" maxOccurs="unbounded" minOccurs="0"/>
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
        "v4Modifier"
    })
    public static class MissModifiers implements Serializable {

        @XmlElement(name = "V4_Modifier")
        protected List<V4Modifier> v4Modifier;

        /**
         * Gets the value of the v4Modifier property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the v4Modifier property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getV4Modifier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link V4Modifier }
         * 
         * 
         */
        public List<V4Modifier> getV4Modifier() {
            if (v4Modifier == null) {
                v4Modifier = new ArrayList<V4Modifier>();
            }
            return this.v4Modifier;
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
     *         &lt;element name="CharacterClass" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="Level" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
        "characterClass",
        "level"
    })
    public static class Requirements implements Serializable {

        @XmlElement(name = "CharacterClass")
        protected List<String> characterClass;
        @XmlElement(name = "Level")
        protected Integer level;

        /**
         * Gets the value of the characterClass property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the characterClass property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCharacterClass().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCharacterClass() {
            if (characterClass == null) {
                characterClass = new ArrayList<String>();
            }
            return this.characterClass;
        }

        /**
         * Gets the value of the level property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getLevel() {
            return level;
        }

        /**
         * Sets the value of the level property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setLevel(Integer value) {
            this.level = value;
        }

    }

}
