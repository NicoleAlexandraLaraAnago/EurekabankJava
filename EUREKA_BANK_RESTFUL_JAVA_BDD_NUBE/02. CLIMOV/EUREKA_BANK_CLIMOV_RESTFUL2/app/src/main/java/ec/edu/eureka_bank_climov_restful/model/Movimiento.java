package ec.edu.eureka_bank_climov_restful.model;

public class Movimiento {
    private String fecha;
    private String tipo;
    private String accion;
    private double importe;

    public String getFecha() {
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
