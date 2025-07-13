
package ec.edu.restfull.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para obtenerAmortizacionPorFactura complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="obtenerAmortizacionPorFactura">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="facturaId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerAmortizacionPorFactura", propOrder = {
    "facturaId"
})
public class ObtenerAmortizacionPorFactura {

    protected int facturaId;

    /**
     * Obtiene el valor de la propiedad facturaId.
     * 
     */
    public int getFacturaId() {
        return facturaId;
    }

    /**
     * Define el valor de la propiedad facturaId.
     * 
     */
    public void setFacturaId(int value) {
        this.facturaId = value;
    }

}
