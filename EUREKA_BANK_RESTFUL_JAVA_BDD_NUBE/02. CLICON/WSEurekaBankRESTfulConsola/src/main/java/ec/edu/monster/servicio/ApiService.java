package ec.edu.monster.servicio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.edu.monster.modelo.Movimiento;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiService {

    private static final String BASE_URL = "http://localhost:8080/WS_EUREKABANK_RESTFULL_JAVA/webresources";

    // ✅ LOGIN
    public static boolean login(String usuario, String contrasena) {
        String url = BASE_URL + "/login";
        String params = "usuario=" + usuario + "&contrasena=" + contrasena;

        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoOutput(true);

            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                out.write(params.getBytes(StandardCharsets.UTF_8));
            }

            int status = con.getResponseCode();
            return status == 200;

        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
            return false;
        }
    }

    // ✅ OBTENER MOVIMIENTOS
    public static List<Movimiento> getMovimientos(String cuenta) {
        try {
            URL url = new URL(BASE_URL + "/coreBancario/movimientos/" + cuenta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                List<Movimiento> lista = new Gson().fromJson(in, new TypeToken<List<Movimiento>>() {}.getType());
                in.close();
                return lista;
            } else {
                System.out.println("No se encontraron movimientos para la cuenta: " + cuenta);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener movimientos: " + e.getMessage());
        }
        return null;
    }

    // ✅ REGISTRAR DEPÓSITO
    public static boolean registrarDeposito(String cuenta, double importe) {
        String params = "cuenta=" + cuenta + "&importe=" + importe;
        return postMovimiento("/coreBancario/deposito", params);
    }

    // ✅ REGISTRAR RETIRO
    public static boolean registrarRetiro(String cuenta, double importe) {
        String params = "cuenta=" + cuenta + "&importe=" + importe;
        return postMovimiento("/coreBancario/retiro", params);
    }

    // ✅ REGISTRAR TRANSFERENCIA
    public static boolean registrarTransferencia(String origen, String destino, double importe) {
        String params = "cuentaOrigen=" + origen + "&cuentaDestino=" + destino + "&importe=" + importe;
        return postMovimiento("/coreBancario/transferencia", params);
    }

    // ✅ OBTENER SALDO CALCULADO
    public static double obtenerSaldo(String cuenta) {
        List<Movimiento> movimientos = getMovimientos(cuenta);
        if (movimientos == null) return 0;

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

    // 🔁 MÉTODO POST COMÚN
    private static boolean postMovimiento(String endpoint, String params) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(BASE_URL + endpoint).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoOutput(true);

            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                out.write(params.getBytes(StandardCharsets.UTF_8));
            }

            return con.getResponseCode() == 200;

        } catch (Exception e) {
            System.err.println("Error al enviar petición POST: " + e.getMessage());
            return false;
        }
    }
}
