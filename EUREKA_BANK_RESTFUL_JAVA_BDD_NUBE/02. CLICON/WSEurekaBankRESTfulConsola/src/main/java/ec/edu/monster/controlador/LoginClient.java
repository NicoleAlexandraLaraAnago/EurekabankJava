package ec.edu.monster.controlador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.monster.modelo.Movimiento;

public class LoginClient {

    private static final String BASE_URL = "http://localhost:8080/WS_EUREKABANK_RESTFULL_JAVA/webresources";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("               LOGIN                  ");
        System.out.println("======================================");
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        if (hacerLogin(usuario, contrasena)) {
            System.out.println("\n[OK] Login exitoso.");
            mostrarMenu(sc);
        } else {
            System.out.println("\n[ERROR] Credenciales incorrectas.");
        }

        sc.close();
    }

    public static boolean hacerLogin(String usuario, String contrasena) {
        try {
            URL url = new URL(BASE_URL + "/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String data = "usuario=" + URLEncoder.encode(usuario, "UTF-8") +
                          "&contrasena=" + URLEncoder.encode(contrasena, "UTF-8");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(data.getBytes(StandardCharsets.UTF_8));
            }

            return conn.getResponseCode() == 200;

        } catch (Exception e) {
            System.err.println("[ERROR] en login: " + e.getMessage());
            return false;
        }
    }

    public static void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n======================================");
            System.out.println("           MENÚ PRINCIPAL             ");
            System.out.println("======================================");
            System.out.println("1. Consultar Movimientos");
            System.out.println("2. Registrar Depósito");
            System.out.println("3. Registrar Retiro");
            System.out.println("4. Registrar Transferencia");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar salto

            switch (opcion) {
                case 1: consultarMovimientos(sc); break;
                case 2: registrarDeposito(sc); break;
                case 3: registrarRetiro(sc); break;
                case 4: registrarTransferencia(sc); break;
                case 0: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public static void consultarMovimientos(Scanner sc) {
        try {
            System.out.print("Ingrese número de cuenta: ");
            String cuenta = sc.nextLine();

            URL url = new URL(BASE_URL + "/coreBancario/movimientos/" + cuenta);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                response.append(linea);
            }
            br.close();

            Gson gson = new Gson();
            List<Movimiento> movimientos = gson.fromJson(response.toString(), new TypeToken<List<Movimiento>>() {}.getType());

            System.out.println("\n========== MOVIMIENTOS ==============");
            System.out.printf("%-10s %-8s %-12s %-20s %-10s %-10s\n",
                    "Cuenta", "NroMov", "Fecha", "Tipo", "Acción", "Importe");

            for (Movimiento m : movimientos) {
                System.out.printf("%-10s %-8d %-12s %-20s %-10s %-10.2f\n",
                        m.getCuenta(), m.getNromov(), m.getFecha(), m.getTipo(),
                        m.getAccion(), m.getImporte());
            }

        } catch (Exception e) {
            System.err.println("[ERROR] consultando movimientos: " + e.getMessage());
        }
    }

    public static void registrarDeposito(Scanner sc) {
        try {
            System.out.print("Ingrese número de cuenta: ");
            String cuenta = sc.nextLine();
            System.out.print("Ingrese importe a depositar: ");
            String importe = sc.nextLine();

            double saldoAntes = obtenerSaldo(cuenta);

            String data = "cuenta=" + URLEncoder.encode(cuenta, "UTF-8") +
                          "&importe=" + URLEncoder.encode(importe, "UTF-8");

            URL url = new URL(BASE_URL + "/coreBancario/deposito");
            enviarPost(data, url);

            double saldoDespues = obtenerSaldo(cuenta);
            System.out.printf("Saldo anterior: $%.2f\n", saldoAntes);
            System.out.printf("Saldo actual:   $%.2f\n", saldoDespues);

        } catch (Exception e) {
            System.err.println("[ERROR] registrando depósito: " + e.getMessage());
        }
    }

    public static void registrarRetiro(Scanner sc) {
        try {
            System.out.print("Ingrese número de cuenta: ");
            String cuenta = sc.nextLine();
            System.out.print("Ingrese importe a retirar: ");
            String importe = sc.nextLine();

            double saldoAntes = obtenerSaldo(cuenta);

            String data = "cuenta=" + URLEncoder.encode(cuenta, "UTF-8") +
                          "&importe=" + URLEncoder.encode(importe, "UTF-8");

            URL url = new URL(BASE_URL + "/coreBancario/retiro");
            enviarPost(data, url);

            double saldoDespues = obtenerSaldo(cuenta);
            System.out.printf("Saldo anterior: $%.2f\n", saldoAntes);
            System.out.printf("Saldo actual:   $%.2f\n", saldoDespues);

        } catch (Exception e) {
            System.err.println("[ERROR] registrando retiro: " + e.getMessage());
        }
    }

    public static void registrarTransferencia(Scanner sc) {
        try {
            System.out.print("Ingrese cuenta origen: ");
            String cuentaOrigen = sc.nextLine();
            System.out.print("Ingrese cuenta destino: ");
            String cuentaDestino = sc.nextLine();
            System.out.print("Ingrese importe a transferir: ");
            String importe = sc.nextLine();

            double saldoAntesOrigen = obtenerSaldo(cuentaOrigen);
            double saldoAntesDestino = obtenerSaldo(cuentaDestino);

            String data = "cuentaOrigen=" + URLEncoder.encode(cuentaOrigen, "UTF-8") +
                          "&cuentaDestino=" + URLEncoder.encode(cuentaDestino, "UTF-8") +
                          "&importe=" + URLEncoder.encode(importe, "UTF-8");

            URL url = new URL(BASE_URL + "/coreBancario/transferencia");
            enviarPost(data, url);

            double saldoDespuesOrigen = obtenerSaldo(cuentaOrigen);
            double saldoDespuesDestino = obtenerSaldo(cuentaDestino);

            System.out.println("\n--- Saldos después de la transferencia ---");
            System.out.printf("Cuenta Origen:\n  Antes: $%.2f\n  Después: $%.2f\n", saldoAntesOrigen, saldoDespuesOrigen);
            System.out.printf("Cuenta Destino:\n  Antes: $%.2f\n  Después: $%.2f\n", saldoAntesDestino, saldoDespuesDestino);

        } catch (Exception e) {
            System.err.println("[ERROR] registrando transferencia: " + e.getMessage());
        }
    }

    private static double obtenerSaldo(String cuenta) {
        try {
            URL url = new URL(BASE_URL + "/coreBancario/movimientos/" + cuenta);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                response.append(linea);
            }
            br.close();

            Gson gson = new Gson();
            List<Movimiento> movimientos = gson.fromJson(response.toString(), new TypeToken<List<Movimiento>>() {}.getType());

            double saldo = 0;
            for (Movimiento m : movimientos) {
                if ("INGRESO".equalsIgnoreCase(m.getAccion())) {
                    saldo += m.getImporte();
                } else {
                    saldo -= m.getImporte();
                }
            }
            return saldo;

        } catch (Exception e) {
            System.err.println("[ERROR] obteniendo saldo: " + e.getMessage());
            return 0;
        }
    }

    private static void enviarPost(String data, URL url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(data.getBytes(StandardCharsets.UTF_8));
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                response.append(linea);
            }
            br.close();

            Gson gson = new Gson();
            Map<String, Object> json = gson.fromJson(response.toString(), Map.class);

            if ("1.0".equals(json.get("estado").toString())) {
                System.out.println("[OK] Operación realizada con éxito.");
            } else {
                System.out.println("[ERROR] Falló la operación.");
            }

        } catch (Exception e) {
            System.err.println("[ERROR] al enviar POST: " + e.getMessage());
        }
    }
}
