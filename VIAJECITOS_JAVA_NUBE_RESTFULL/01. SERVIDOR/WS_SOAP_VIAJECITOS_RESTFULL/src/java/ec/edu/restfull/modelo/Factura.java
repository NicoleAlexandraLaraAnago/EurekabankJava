/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.restfull.modelo;

import java.util.Date;

import java.util.List;

/**
 *
 * @author Sebastian
 */
public class Factura {
    private int id;
    private int compraId;
    private Date fechaEmision;
    private Compra compra;
    private Usuario usuario;
    private MetodoPago metodoPago;
    private List<Amortizacion> amortizaciones;

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

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<Amortizacion> getAmortizaciones() {
        return amortizaciones;
    }

    public void setAmortizaciones(List<Amortizacion> amortizaciones) {
        this.amortizaciones = amortizaciones;
    }
    
}
