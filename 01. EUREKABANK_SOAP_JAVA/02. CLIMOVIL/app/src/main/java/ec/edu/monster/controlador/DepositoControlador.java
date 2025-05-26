package ec.edu.monster.controlador;

import ec.edu.monster.modelo.DepositoService;

public class DepositoControlador {
    private final DepositoService servicio;

    public DepositoControlador() {
        this.servicio = new DepositoService();
    }

    public String registrarDep(String cuenta, double importe) {
        String estado = servicio.registrarDeposito(cuenta, importe);
        if (estado != null) {
            return estado;
        }
        return "Error, en con el servicio.";
    }

}
