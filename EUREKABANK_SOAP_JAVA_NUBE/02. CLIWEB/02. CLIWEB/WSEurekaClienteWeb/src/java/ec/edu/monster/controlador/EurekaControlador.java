package ec.edu.monster.controlador;

import ec.edu.monster.service.EurekaService;

public class EurekaControlador {
    private final EurekaService service = new EurekaService();

    public int regDeposito(String cuenta, double importe) {
        return service.regDeposito(cuenta, importe);
    }
}
