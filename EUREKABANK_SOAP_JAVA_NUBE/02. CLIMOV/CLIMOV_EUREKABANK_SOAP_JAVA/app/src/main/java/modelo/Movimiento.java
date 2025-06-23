package modelo;

import java.util.Date;

public class Movimiento {
    private String cuenta;
    private int nromov;
    private Date fecha;
    private String tipo;
    private String accion;
    private double importe;

    public Movimiento(String cuenta, int nromov, Date fecha, String tipo, String accion, double importe) {
        this.cuenta = cuenta;
        this.nromov = nromov;
        this.fecha = fecha;
        this.tipo = tipo;
        this.accion = accion;
        this.importe = importe;
    }

    public String getCuenta() {
        return cuenta;
    }

    public int getNromov() {
        return nromov;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAccion() {
        return accion;
    }

    public double getImporte() {
        return importe;
    }
}
