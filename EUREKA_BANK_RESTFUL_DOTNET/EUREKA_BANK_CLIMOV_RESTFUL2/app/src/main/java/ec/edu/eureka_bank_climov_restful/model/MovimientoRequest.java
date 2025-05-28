package ec.edu.eureka_bank_climov_restful.model;

public class MovimientoRequest {

    private String cuentaOrigen;
    private String cuentaDestino;
    private String tipoMovimiento;
    private double importe;

    // Setter y getter para cuentaOrigen
    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    // Setter y getter para cuentaDestino
    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    // Setter y getter para tipoMovimiento
    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    // Setter y getter para importe
    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}
