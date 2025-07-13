
package ec.edu.gr02.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para detalleCompra complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="detalleCompra">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidadAsientos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="compraId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subtotalVuelo" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
@XmlType(name = "detalleCompra", propOrder = {
    "cantidadAsientos",
    "compraId",
    "id",
    "subtotalVuelo",
    "vueloId"
})
public class DetalleCompra {

    protected int cantidadAsientos;
    protected int compraId;
    protected int id;
    protected double subtotalVuelo;
    protected int vueloId;

    /**
     * Obtiene el valor de la propiedad cantidadAsientos.
     * 
     */
    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    /**
     * Define el valor de la propiedad cantidadAsientos.
     * 
     */
    public void setCantidadAsientos(int value) {
        this.cantidadAsientos = value;
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
     * Obtiene el valor de la propiedad subtotalVuelo.
     * 
     */
    public double getSubtotalVuelo() {
        return subtotalVuelo;
    }

    /**
     * Define el valor de la propiedad subtotalVuelo.
     * 
     */
    public void setSubtotalVuelo(double value) {
        this.subtotalVuelo = value;
    }

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
