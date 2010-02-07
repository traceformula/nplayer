//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b18-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.06.22 at 12:39:58 BST 
//


package rpg.jaxb;


/**
 * Java content class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/Documents%20and%20Settings/Sven-Ivar%20Fjeld/Mine%20dokumenter/_Development/Java/GameMaster/xml/schemas/all.xsd line 39)
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ArmorShield" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ACBonus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="MaxDexBonus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="ArmorCheckPenalty" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="ArcaneSpellFailure" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Speed">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ft30" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="ft20" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Weight">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Kilograms" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="Lbs" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="SpecialProperties" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PropertyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="PropertyType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="PropertyBonus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="PropertyTarget" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
 */
public interface ProtectiveItemType {


    /**
     * Gets the value of the ArmorShield property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ArmorShield property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArmorShield().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link rpg.jaxb.ProtectiveItemType.ArmorShieldType}
     * 
     */
    java.util.List getArmorShield();


    /**
     * Java content class for anonymous complex type.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/Documents%20and%20Settings/Sven-Ivar%20Fjeld/Mine%20dokumenter/_Development/Java/GameMaster/xml/schemas/all.xsd line 42)
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ACBonus" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="MaxDexBonus" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="ArmorCheckPenalty" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="ArcaneSpellFailure" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="Speed">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ft30" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="ft20" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Weight">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Kilograms" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="Lbs" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="SpecialProperties" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="PropertyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="PropertyType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="PropertyBonus" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="PropertyTarget" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
     */
    public interface ArmorShieldType {


        /**
         * Gets the value of the armorCheckPenalty property.
         * 
         */
        int getArmorCheckPenalty();

        /**
         * Sets the value of the armorCheckPenalty property.
         * 
         */
        void setArmorCheckPenalty(int value);

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getType();

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setType(java.lang.String value);

        /**
         * Gets the value of the weight property.
         * 
         * @return
         *     possible object is
         *     {@link rpg.jaxb.ProtectiveItemType.ArmorShieldType.WeightType}
         */
        rpg.jaxb.ProtectiveItemType.ArmorShieldType.WeightType getWeight();

        /**
         * Sets the value of the weight property.
         * 
         * @param value
         *     allowed object is
         *     {@link rpg.jaxb.ProtectiveItemType.ArmorShieldType.WeightType}
         */
        void setWeight(rpg.jaxb.ProtectiveItemType.ArmorShieldType.WeightType value);

        /**
         * Gets the value of the arcaneSpellFailure property.
         * 
         */
        int getArcaneSpellFailure();

        /**
         * Sets the value of the arcaneSpellFailure property.
         * 
         */
        void setArcaneSpellFailure(int value);

        /**
         * Gets the value of the speed property.
         * 
         * @return
         *     possible object is
         *     {@link rpg.jaxb.ProtectiveItemType.ArmorShieldType.SpeedType}
         */
        rpg.jaxb.ProtectiveItemType.ArmorShieldType.SpeedType getSpeed();

        /**
         * Sets the value of the speed property.
         * 
         * @param value
         *     allowed object is
         *     {@link rpg.jaxb.ProtectiveItemType.ArmorShieldType.SpeedType}
         */
        void setSpeed(rpg.jaxb.ProtectiveItemType.ArmorShieldType.SpeedType value);

        /**
         * Gets the value of the SpecialProperties property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the SpecialProperties property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSpecialProperties().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link rpg.jaxb.ProtectiveItemType.ArmorShieldType.SpecialPropertiesType}
         * 
         */
        java.util.List getSpecialProperties();

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getName();

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setName(java.lang.String value);

        /**
         * Gets the value of the acBonus property.
         * 
         */
        int getACBonus();

        /**
         * Sets the value of the acBonus property.
         * 
         */
        void setACBonus(int value);

        /**
         * Gets the value of the maxDexBonus property.
         * 
         */
        int getMaxDexBonus();

        /**
         * Sets the value of the maxDexBonus property.
         * 
         */
        void setMaxDexBonus(int value);


        /**
         * Java content class for anonymous complex type.
         * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/Documents%20and%20Settings/Sven-Ivar%20Fjeld/Mine%20dokumenter/_Development/Java/GameMaster/xml/schemas/all.xsd line 67)
         * <p>
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="PropertyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="PropertyType" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="PropertyBonus" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="PropertyTarget" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         */
        public interface SpecialPropertiesType {


            /**
             * Gets the value of the propertyTarget property.
             * 
             * @return
             *     possible object is
             *     {@link java.lang.String}
             */
            java.lang.String getPropertyTarget();

            /**
             * Sets the value of the propertyTarget property.
             * 
             * @param value
             *     allowed object is
             *     {@link java.lang.String}
             */
            void setPropertyTarget(java.lang.String value);

            /**
             * Gets the value of the propertyType property.
             * 
             * @return
             *     possible object is
             *     {@link java.lang.String}
             */
            java.lang.String getPropertyType();

            /**
             * Sets the value of the propertyType property.
             * 
             * @param value
             *     allowed object is
             *     {@link java.lang.String}
             */
            void setPropertyType(java.lang.String value);

            /**
             * Gets the value of the propertyBonus property.
             * 
             */
            int getPropertyBonus();

            /**
             * Sets the value of the propertyBonus property.
             * 
             */
            void setPropertyBonus(int value);

            /**
             * Gets the value of the propertyName property.
             * 
             * @return
             *     possible object is
             *     {@link java.lang.String}
             */
            java.lang.String getPropertyName();

            /**
             * Sets the value of the propertyName property.
             * 
             * @param value
             *     allowed object is
             *     {@link java.lang.String}
             */
            void setPropertyName(java.lang.String value);

        }


        /**
         * Java content class for anonymous complex type.
         * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/Documents%20and%20Settings/Sven-Ivar%20Fjeld/Mine%20dokumenter/_Development/Java/GameMaster/xml/schemas/all.xsd line 51)
         * <p>
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="ft30" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="ft20" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         */
        public interface SpeedType {


            /**
             * Gets the value of the ft20 property.
             * 
             */
            int getFt20();

            /**
             * Sets the value of the ft20 property.
             * 
             */
            void setFt20(int value);

            /**
             * Gets the value of the ft30 property.
             * 
             */
            int getFt30();

            /**
             * Sets the value of the ft30 property.
             * 
             */
            void setFt30(int value);

        }


        /**
         * Java content class for anonymous complex type.
         * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/Documents%20and%20Settings/Sven-Ivar%20Fjeld/Mine%20dokumenter/_Development/Java/GameMaster/xml/schemas/all.xsd line 59)
         * <p>
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Kilograms" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="Lbs" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         */
        public interface WeightType {


            /**
             * Gets the value of the kilograms property.
             * 
             */
            double getKilograms();

            /**
             * Sets the value of the kilograms property.
             * 
             */
            void setKilograms(double value);

            /**
             * Gets the value of the lbs property.
             * 
             */
            double getLbs();

            /**
             * Sets the value of the lbs property.
             * 
             */
            void setLbs(double value);

        }

    }

}