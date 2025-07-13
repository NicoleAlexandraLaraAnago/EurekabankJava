/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.restfull.modelo;

/**
 *
 * @author Sebastian
 */
public class DetalleCompra {
    private int id;
    private int compraId;
    private int vueloId;
    private int cantidadAsientos;
    private double subtotalVuelo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public int getVueloId() {
        return vueloId;
    }

    public void setVueloId(int vueloId) {
        this.vueloId = vueloId;
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public void setCantidadAsientos(int cantidadAsientos) {
        this.cantidadAsientos = cantidadAsientos;
    }

    public double getSubtotalVuelo() {
        return subtotalVuelo;
    }

    public void setSubtotalVuelo(double subtotalVuelo) {
        this.subtotalVuelo = subtotalVuelo;
    }
    
}
