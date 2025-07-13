
package ec.edu.restfull.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para amortizacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="amortizacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estadoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="facturaId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fechaPago" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="montoCuota" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="numeroCuota" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="saldoRestante" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "amortizacion", propOrder = {
    "estadoPago",
    "facturaId",
    "fechaPago",
    "id",
    "montoCuota",
    "numeroCuota",
    "saldoRestante"
})
public class Amortizacion {

    protected String estadoPago;
    protected int facturaId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaPago;
    protected int id;
    protected double montoCuota;
    protected int numeroCuota;
    protected double saldoRestante;

    /**
     * Obtiene el valor de la propiedad estadoPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoPago() {
        return estadoPago;
    }

    /**
     * Define el valor de la propiedad estadoPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoPago(String value) {
        this.estadoPago = value;
    }

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

    /**
     * Obtiene el valor de la propiedad fechaPago.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaPago() {
        return fechaPago;
    }

    /**
     * Define el valor de la propiedad fechaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaPago(XMLGregorianCalendar value) {
        this.fechaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad montoCuota.
     * 
     */
    public double getMontoCuota() {
        return montoCuota;
    }

    /**
     * Define el valor de la propiedad montoCuota.
     * 
     */
    public void setMontoCuota(double value) {
        this.montoCuota = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroCuota.
     * 
     */
    public int getNumeroCuota() {
        return numeroCuota;
    }

    /**
     * Define el valor de la propiedad numeroCuota.
     * 
     */
    public void setNumeroCuota(int value) {
        this.numeroCuota = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoRestante.
     * 
     */
    public double getSaldoRestante() {
        return saldoRestante;
    }

    /**
     * Define el valor de la propiedad saldoRestante.
     * 
     */
    public void setSaldoRestante(double value) {
        this.saldoRestante = value;
    }

}
