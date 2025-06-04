package servicio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelo.Vuelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VueloServicio {

   public List<Vuelo> buscarVuelos(String origen, String destino, String fecha) throws IOException {
    // Convertir fecha de dd/MM/yyyy a yyyy-MM-dd si es necesario
    if (fecha.contains("/")) {
        String[] partes = fecha.split("/");
        fecha = partes[2] + "-" + partes[1] + "-" + partes[0]; // yyyy-MM-dd
    }

    String endpoint = String.format("http://10.40.15.238:8080/vuelos?origen=%s&destino=%s&fecha=%s",
            origen, destino, fecha);
    URL url = new URL(endpoint);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("Accept", "application/json");

    if (con.getResponseCode() == 200) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            Type listType = new TypeToken<List<Vuelo>>() {}.getType();
            List<Vuelo> vuelos = new Gson().fromJson(in, listType);

            vuelos.sort(Comparator.comparingDouble(Vuelo::getValor).reversed());

            return vuelos;
        }
    }

    return Collections.emptyList();
}

}
