package servicio;

import com.google.gson.Gson;
import modelo.Compra;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CompraServicio {
    private static final String URL_BACKEND = "http://192.168.100.36:8080/compras";
    private final Gson gson = new Gson();

    public boolean realizarCompra(Compra compra) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(URL_BACKEND);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            // Convertir objeto Compra a JSON
            String jsonInput = gson.toJson(compra);

            // Enviar JSON al backend
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);

            return responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED;

        } catch (Exception e) {
            System.err.println("Error al realizar la compra: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
