package ec.edu.monster.servicio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.monster.modelo.Movimiento;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiService {

    private static final String BASE_URL = "http://localhost:8080/WS_EUREKABANK_RESTFULL_JAVA/webresources";

    public static boolean login(String usuario, String contrasena) throws IOException {
        String params = "usuario=" + URLEncoder.encode(usuario, "UTF-8") +
                        "&contrasena=" + URLEncoder.encode(contrasena, "UTF-8");
        URL url = new URL(BASE_URL + "/login");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        try (OutputStream os = conn.getOutputStream()) {
            os.write(params.getBytes(StandardCharsets.UTF_8));
        }
        return conn.getResponseCode() == 200;
    }

    public static List<Movimiento> obtenerMovimientos(String cuenta) throws Exception {
        URL url = new URL(BASE_URL + "/coreBancario/movimientos/" + cuenta);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        Gson gson = new Gson();
        List<Movimiento> movimientos = gson.fromJson(response.toString(), new TypeToken<List<Movimiento>>() {}.getType());

        // Ordenar por fecha descendente (más reciente primero)
        movimientos.sort((m1, m2) -> m2.getFecha().compareTo(m1.getFecha()));

        return movimientos;
    }


    public static double obtenerSaldo(String cuenta) throws Exception {
        List<Movimiento> movimientos = obtenerMovimientos(cuenta);
        double saldo = 0;
        for (Movimiento m : movimientos) {
            if ("INGRESO".equalsIgnoreCase(m.getAccion())) {
                saldo += m.getImporte();
            } else {
                saldo -= m.getImporte();
            }
        }
        return saldo;
    }

    public static boolean registrarDeposito(String cuenta, double importe) throws IOException {
        String data = "cuenta=" + URLEncoder.encode(cuenta, "UTF-8") +
                      "&importe=" + URLEncoder.encode(String.valueOf(importe), "UTF-8");
        URL url = new URL(BASE_URL + "/coreBancario/deposito");
        return enviarPOST(url, data);
    }

    public static boolean registrarRetiro(String cuenta, double importe) throws IOException {
        String data = "cuenta=" + URLEncoder.encode(cuenta, "UTF-8") +
                      "&importe=" + URLEncoder.encode(String.valueOf(importe), "UTF-8");
        URL url = new URL(BASE_URL + "/coreBancario/retiro");
        return enviarPOST(url, data);
    }

    public static boolean registrarTransferencia(String origen, String destino, double importe) throws IOException {
        String data = "cuentaOrigen=" + URLEncoder.encode(origen, "UTF-8") +
                      "&cuentaDestino=" + URLEncoder.encode(destino, "UTF-8") +
                      "&importe=" + URLEncoder.encode(String.valueOf(importe), "UTF-8");
        URL url = new URL(BASE_URL + "/coreBancario/transferencia");
        return enviarPOST(url, data);
    }

    private static boolean enviarPOST(URL url, String data) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        return conn.getResponseCode() == 200;
    }
}
