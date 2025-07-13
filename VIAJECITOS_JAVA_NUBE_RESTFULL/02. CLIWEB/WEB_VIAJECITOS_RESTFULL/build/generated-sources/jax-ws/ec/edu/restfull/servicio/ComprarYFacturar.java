
package ec.edu.restfull.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para comprarYFacturar complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="comprarYFacturar"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="compra" type="{http://servicio.restfull.edu.ec/}compra" minOccurs="0"/&gt;
 *         &lt;element name="tipoAmortizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cuotas" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="tasaAnual" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comprarYFacturar", propOrder = {
    "compra",
    "tipoAmortizacion",
    "cuotas",
    "tasaAnual"
})
public class ComprarYFacturar {

    protected Compra compra;
    protected String tipoAmortizacion;
    protected int cuotas;
    protected double tasaAnual;

    /**
     * Obtiene el valor de la propiedad compra.
     * 
     * @return
     *     possible object is
     *     {@link Compra }
     *     
     */
    public Compra getCompra() {
        return compra;
    }

    /**
     * Define el valor de la propiedad compra.
     * 
     * @param value
     *     allowed object is
     *     {@link Compra }
     *     
     */
    public void setCompra(Compra value) {
        this.compra = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoAmortizacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAmortizacion() {
        return tipoAmortizacion;
    }

    /**
     * Define el valor de la propiedad tipoAmortizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAmortizacion(String value) {
        this.tipoAmortizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad cuotas.
     * 
     */
    public int getCuotas() {
        return cuotas;
    }

    /**
     * Define el valor de la propiedad cuotas.
     * 
     */
    public void setCuotas(int value) {
        this.cuotas = value;
    }

    /**
     * Obtiene el valor de la propiedad tasaAnual.
     * 
     */
    public double getTasaAnual() {
        return tasaAnual;
    }

    /**
     * Define el valor de la propiedad tasaAnual.
     * 
     */
    public void setTasaAnual(double value) {
        this.tasaAnual = value;
    }

}
