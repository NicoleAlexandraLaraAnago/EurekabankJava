package servicio;

import java.net.*;
import java.io.*;
import com.google.gson.*;
import java.util.Map;
import modelo.LoginRespuesta;
import modelo.Usuario;

public class UsuarioServicio {
    public LoginRespuesta login(String correo, String password) throws IOException {
        URL url = new URL("http://10.40.15.238:8080/auth/login"); // ⚠️ URL corregida según tu backend
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        String inputJson = new Gson().toJson(Map.of("correo", correo, "password", password));
        try (OutputStream os = con.getOutputStream()) {
            os.write(inputJson.getBytes("utf-8"));
        }

        if (con.getResponseCode() == 200) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                LoginRespuesta respuesta = new Gson().fromJson(in, LoginRespuesta.class);
                return respuesta;
            }
        }
        return null;
    }
    public boolean registrar(Usuario usuario) throws IOException {
    URL url = new URL("http://10.40.15.238:8080/auth/registro");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("POST");
    con.setRequestProperty("Content-Type", "application/json");
    con.setDoOutput(true);

    String inputJson = new Gson().toJson(usuario);
    try (OutputStream os = con.getOutputStream()) {
        os.write(inputJson.getBytes("utf-8"));
    }

    return con.getResponseCode() == 200;
}

}
