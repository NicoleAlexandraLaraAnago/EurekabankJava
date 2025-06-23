
package ec.edu.monster.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para obtenerSaldoResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="obtenerSaldoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerSaldoResponse", propOrder = {
    "saldo"
})
public class ObtenerSaldoResponse {

    protected double saldo;

    /**
     * Obtiene el valor de la propiedad saldo.
     * 
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Define el valor de la propiedad saldo.
     * 
     */
    public void setSaldo(double value) {
        this.saldo = value;
    }

}
