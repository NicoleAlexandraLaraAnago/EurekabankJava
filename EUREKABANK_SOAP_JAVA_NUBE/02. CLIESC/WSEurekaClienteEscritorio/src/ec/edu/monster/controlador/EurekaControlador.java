package ec.edu.monster.controlador;
import ec.edu.monster.service.EurekaService;
import ec.edu.monster.ws.Movimiento;
import java.util.List;

public class EurekaControlador {
    
    public List<Movimiento> traerMoviminetos(String cuenta) {
        EurekaService service = new EurekaService();
        return service.traerMovimientos(cuenta);
    }

    public double obtenerSaldo(String cuenta) {
        EurekaService service = new EurekaService();
        return service.obtenerSaldo(cuenta);
    }
    
    public int regDeposito(String cuenta, double deposito, String tipoCodigo) {
        EurekaService service = new EurekaService();
        return service.regDeposito(cuenta, tipoCodigo, deposito);
    }

    // Método para validar saldo antes de proceder con un retiro o transferencia
    private boolean validarSaldoSuficiente(String cuenta, double monto) {
        double saldoActual = obtenerSaldo(cuenta); // Usando el nuevo método obtenerSaldo
        return saldoActual >= monto;
    }

    public int regRetiro(String cuenta, double monto, String tipoCodigo) {
        // Primero validamos si hay saldo suficiente
        if (!validarSaldoSuficiente(cuenta, monto)) {
            return -2; // Código -2 indica saldo insuficiente
        }
        // Procedemos a realizar el retiro
        EurekaService service = new EurekaService();
        return service.regDeposito(cuenta, tipoCodigo, monto);
    }

    public int regTransferencia(String cuentaOrigen, String cuentaDestino, double monto, String tipoCodigo) {
        // Primero validamos si hay saldo suficiente en la cuenta origen
        if (!validarSaldoSuficiente(cuentaOrigen, monto)) {
            return -2; // Código -2 indica saldo insuficiente
        }

        EurekaService service = new EurekaService();
        
        // Realizamos el retiro en la cuenta origen usando código 009 (SALIDA)
        int resultadoRetiro = service.regDeposito(cuentaOrigen, "009", monto);
        
        if (resultadoRetiro == 1) {
            // Si el retiro fue exitoso, realizamos el depósito en la cuenta destino usando código 008 (INGRESO)
            int resultadoDeposito = service.regDeposito(cuentaDestino, "008", monto);
            
            if (resultadoDeposito == 1) {
                return 1; // Transferencia exitosa
            } else {
                // Si el depósito falló, revertimos el retiro
                service.regDeposito(cuentaOrigen, "003", monto); // Revertimos usando código de depósito
                return -3; // Error en el depósito
            }
        }
        return -1; // Error en el proceso de retiro
    }
    
    public int login(String usuario, String contraseña) {
        EurekaService service = new EurekaService();
        return service.login(usuario, contraseña);
    }
}