
package ec.edu.restfull.servicio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para factura complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="factura"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amortizaciones" type="{http://servicio.restfull.edu.ec/}amortizacion" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="compra" type="{http://servicio.restfull.edu.ec/}compra" minOccurs="0"/&gt;
 *         &lt;element name="compraId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="fechaEmision" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="metodoPago" type="{http://servicio.restfull.edu.ec/}metodoPago" minOccurs="0"/&gt;
 *         &lt;element name="usuario" type="{http://servicio.restfull.edu.ec/}usuario" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "factura", propOrder = {
    "amortizaciones",
    "compra",
    "compraId",
    "fechaEmision",
    "id",
    "metodoPago",
    "usuario"
})
public class Factura {

    @XmlElement(nillable = true)
    protected List<Amortizacion> amortizaciones;
    protected Compra compra;
    protected int compraId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaEmision;
    protected int id;
    protected MetodoPago metodoPago;
    protected Usuario usuario;

    /**
     * Gets the value of the amortizaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amortizaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmortizaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Amortizacion }
     * 
     * 
     */
    public List<Amortizacion> getAmortizaciones() {
        if (amortizaciones == null) {
            amortizaciones = new ArrayList<Amortizacion>();
        }
        return this.amortizaciones;
    }

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
     * Obtiene el valor de la propiedad compraId.
     * 
     */
    public int getCompraId() {
        return compraId;
    }

    /**
     * Define el valor de la propiedad compraId.
     * 
     */
    public void setCompraId(int value) {
        this.compraId = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEmision.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Define el valor de la propiedad fechaEmision.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEmision(XMLGregorianCalendar value) {
        this.fechaEmision = value;
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
     * Obtiene el valor de la propiedad metodoPago.
     * 
     * @return
     *     possible object is
     *     {@link MetodoPago }
     *     
     */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    /**
     * Define el valor de la propiedad metodoPago.
     * 
     * @param value
     *     allowed object is
     *     {@link MetodoPago }
     *     
     */
    public void setMetodoPago(MetodoPago value) {
        this.metodoPago = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuario(Usuario value) {
        this.usuario = value;
    }

}
