package wseurekaclienteconsola;

import ec.edu.monster.controlador.EurekaControlador;
import ec.edu.monster.ws.Movimiento;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import javax.xml.datatype.XMLGregorianCalendar;

public class WSEurekaClienteConsola {

    private static HashMap<String, String> movimientoCodigos;
    private static EurekaControlador controlador;

    public static void main(String[] args) {
        controlador = new EurekaControlador(); 
        Scanner scanner = new Scanner(System.in);

        System.out.print("EUREKABANK \n");
        System.out.print("Ingrese el usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();

        if (login(usuario, contrasena)) {
            System.out.println("¡Bienvenido!");
            mostrarMenu(scanner);
        } else {
            System.out.println("Login fallido");
        }
    }

    private static void initializeMovimientoCodigos() {
        movimientoCodigos = new HashMap<>();
        movimientoCodigos.put("001", "Apertura de Cuenta");
        movimientoCodigos.put("002", "Cancelar Cuenta");
        movimientoCodigos.put("003", "Depósito");
        movimientoCodigos.put("004", "Retiro");
        movimientoCodigos.put("005", "Interés");
        movimientoCodigos.put("006", "Mantenimiento");
        movimientoCodigos.put("007", "ITF");
        // 008 se maneja internamente para transferencias de ingreso
        movimientoCodigos.put("009", "Transferencia");
        movimientoCodigos.put("010", "Cargo por Movimiento");
    }

    private static boolean login(String usuario, String contrasena) {
        int resultado = controlador.login(usuario, contrasena);
        return resultado == 1;
    }

    private static void mostrarMenu(Scanner scanner) {
        int opcion = -1;
        while (opcion != 3) {
            System.out.println("\nMenú de Servicios Eurekabank");
            System.out.println("1. Realizar Movimiento");
            System.out.println("2. Consultar Movimientos");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    realizarMovimiento(scanner);
                    break;
                case 2:
                    consultarMovimientos(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    
    private static void realizarMovimiento(Scanner scanner) {
        System.out.println("\nSeleccione el código del tipo de movimiento:");
        initializeMovimientoCodigos();

        for (String codigo : movimientoCodigos.keySet()) {
            System.out.println(codigo + ": " + movimientoCodigos.get(codigo));
        }

        System.out.print("Ingrese el código de movimiento: ");
        String codigoMovimiento = scanner.nextLine();

        if (movimientoCodigos.containsKey(codigoMovimiento)) {
            System.out.print("Número de cuenta: ");
            String cuenta = scanner.nextLine();

            if (codigoMovimiento.equals("003") || codigoMovimiento.equals("004")) {
                System.out.print("Monto: ");
                double importe = scanner.nextDouble();
                scanner.nextLine();

                if (codigoMovimiento.equals("003")) {
                    realizarDeposito(cuenta, importe, codigoMovimiento);
                } else {
                    realizarRetiro(cuenta, importe);
                }
            }
            else if (codigoMovimiento.equals("009")) {
                System.out.print("Monto a transferir: ");
                double importe = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Número de cuenta destino: ");
                String cuentaDestino = scanner.nextLine();

                realizarTransferencia(cuenta, cuentaDestino, importe);
            } else {
                System.out.println("Este tipo de movimiento no está implementado aún.");
            }
        } else {
            System.out.println("Código de movimiento no válido.");
        }
    }

    private static void consultarMovimientos(Scanner scanner) {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine();

        List<Movimiento> movimientos = controlador.traerMoviminetos(cuenta);
        if (movimientos.isEmpty()) {
            System.out.println("No se encontraron movimientos para la cuenta: " + cuenta);
        } else {
            System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s",
                                           "CUENTA N°", "MOVIMIENTO", "FECHA", "TIPO", "ACCIÓN", "IMPORTE"));

            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (Movimiento mov : movimientos) {
                try {
                    XMLGregorianCalendar xmlDate = mov.getFecha();
                    Date fechaDate = xmlDate.toGregorianCalendar().getTime();
                    String fechaFormateada = outputDateFormat.format(fechaDate);

                    System.out.println(String.format("%-15s %-15d %-15s %-15s %-15s %-15.2f",
                                                   mov.getCuenta(), mov.getNromov(), fechaFormateada,
                                                   mov.getTipo(), mov.getAccion(), mov.getImporte()));
                } catch (Exception e) {
                    System.out.println("Error al convertir la fecha: " + mov.getFecha());
                }
            }
        }
    }

    private static void realizarDeposito(String cuenta, double importe, String tipoCodigo) {
        int resultado = controlador.regDeposito(cuenta, importe, tipoCodigo);
        if (resultado == 1) {
            System.out.println("Depósito realizado con éxito en la cuenta: " + cuenta);
        } else {
            System.out.println("Error al realizar el depósito");
        }
    }

    private static void realizarRetiro(String cuenta, double importe) {
        int resultado = controlador.regRetiro(cuenta, importe, "004");
        if (resultado == 1) {
            System.out.println("Retiro realizado con éxito en la cuenta: " + cuenta);
        } else if (resultado == -2) {
            System.out.println("Error: saldo insuficiente para realizar el retiro.");
        } else {
            System.out.println("Error al realizar el retiro");
        }
    }

    private static void realizarTransferencia(String cuentaOrigen, String cuentaDestino, double importe) {
        // Verificar saldo
        double saldo = controlador.obtenerSaldo(cuentaOrigen);
        
        if (saldo < importe) {
            System.out.println("Error: saldo insuficiente para realizar la transferencia.");
            return;
        }

        // Registrar salida en cuenta origen
        int resultadoSalida = controlador.regDeposito(cuentaOrigen, importe, "009");
        
        if (resultadoSalida == 1) {
            // Si la salida fue exitosa, registrar ingreso en cuenta destino
            int resultadoIngreso = controlador.regDeposito(cuentaDestino, importe, "008");
            
            if (resultadoIngreso == 1) {
                System.out.println("Transferencia realizada con éxito entre las cuentas: " + cuentaOrigen + " y " + cuentaDestino);
            } else {
                // Si falla el ingreso, revertir la salida
                controlador.regDeposito(cuentaOrigen, importe, "003");
                System.out.println("Error al realizar la transferencia en la cuenta destino. Se ha revertido la operación.");
            }
        } else {
            System.out.println("Error al realizar la transferencia en la cuenta origen.");
        }
    }
}