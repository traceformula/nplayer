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
 *         &lt;element ref="{rgb.jaxb.character}V4_CheckCombiner" maxOccurs="unbounded" minOccurs="0"/>
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
    "v4CheckCombiner"
})
@XmlRootElement(name = "V4_ConditionalModifier")
public class V4ConditionalModifier {

    @XmlElement(name = "V4_CheckCombiner")
    protected List<V4CheckCombiner> v4CheckCombiner;

    /**
     * Gets the value of the v4CheckCombiner property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the v4CheckCombiner property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getV4CheckCombiner().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link V4CheckCombiner }
     * 
     * 
     */
    public List<V4CheckCombiner> getV4CheckCombiner() {
        if (v4CheckCombiner == null) {
            v4CheckCombiner = new ArrayList<V4CheckCombiner>();
        }
        return this.v4CheckCombiner;
    }

}