
package ec.edu.gr02.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para buscarVueloPorId complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="buscarVueloPorId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vueloId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscarVueloPorId", propOrder = {
    "vueloId"
})
public class BuscarVueloPorId {

    protected int vueloId;

    /**
     * Obtiene el valor de la propiedad vueloId.
     * 
     */
    public int getVueloId() {
        return vueloId;
    }

    /**
     * Define el valor de la propiedad vueloId.
     * 
     */
    public void setVueloId(int value) {
        this.vueloId = value;
    }

}
