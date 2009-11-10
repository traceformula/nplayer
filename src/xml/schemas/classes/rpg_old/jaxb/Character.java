//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b18-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.06.22 at 12:39:58 BST 
//


package rpg.jaxb;


/**
 * Contains all data of one entity
 * Java content class for Character element declaration.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/C:/Documents%20and%20Settings/Sven-Ivar%20Fjeld/Mine%20dokumenter/_Development/Java/GameMaster/xml/schemas/all.xsd line 435)
 * <p>
 * <pre>
 * &lt;element name="Character">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="Description">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="CharacterName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="PlayerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="CharacterClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="Race" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="Alignment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="Deity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="TotalLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                     &lt;element name="Age" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="EyeColor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="HairColor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="Ability" maxOccurs="6" minOccurs="6">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="Score" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                     &lt;element name="TmpScore" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="SavingThrow" maxOccurs="3" minOccurs="3">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="AbilityDependency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                     &lt;element name="BaseSave" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                     &lt;element name="AbilityModifier" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                     &lt;element name="MagicModifier" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element name="MiscModifier" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element name="TempModifier" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="CombatData">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="AttackBonus" maxOccurs="unbounded" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="TotalAttackBonus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                               &lt;element name="BaseAttackBonus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                               &lt;element name="AbilityModifier" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                               &lt;element name="SizeModifier" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                               &lt;element name="MiscModifier" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *                               &lt;element name="TempModifier" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;/sequence>
 *                             &lt;attribute name="AbilityDependency" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="Wounds" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                     &lt;element name="ArmorClass">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                               &lt;element name="ArmorBonus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                               &lt;element name="ShieldBonus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                               &lt;element name="DexModifier" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                               &lt;element name="SizeModifier" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                               &lt;element name="MiscModifier" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *                               &lt;element name="BaseScore" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;/sequence>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="Initiative">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                               &lt;element name="AbilityModifier" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                               &lt;element name="MiscModifier" maxOccurs="unbounded" minOccurs="0">
 *                                 &lt;complexType>
 *                                   &lt;simpleContent>
 *                                     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
 *                                       &lt;attribute name="Modifier" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                                       &lt;attribute name="Source" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;/extension>
 *                                   &lt;/simpleContent>
 *                                 &lt;/complexType>
 *                               &lt;/element>
 *                             &lt;/sequence>
 *                             &lt;attribute name="AbilityDependency" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="Speed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="Inventory">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="EquippedItem" maxOccurs="unbounded" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;attribute name="BodyPartName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                             &lt;attribute name="ItemName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="UnequippedItem" maxOccurs="unbounded" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;attribute name="ItemName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="SkillListLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="Level" maxOccurs="unbounded" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="ClassName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="HPThisLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                     &lt;element name="Ability" maxOccurs="unbounded" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="AbilityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                               &lt;element name="PointsIncreasedThisLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;/sequence>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="FeatsSelectedThisLevel" maxOccurs="unbounded" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="FeatName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                               &lt;element name="Special" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;/sequence>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element ref="{rgb.jaxb.character}SkillList"/>
 *           &lt;element name="SelectedFeats" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="FeatName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 */
public interface Character
    extends javax.xml.bind.Element, rpg.jaxb.CharacterType
{


}
