package ec.edu.monster.controlador;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DepositoServlet extends HttpServlet {

    private final String BASE_URL = "http://localhost:8080/WS_EUREKABANK_RESTFULL_JAVA/webresources";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cuenta = request.getParameter("cuenta");
        String importe = request.getParameter("importe");
        String tipo = request.getParameter("tipoMovimiento");
        String destino = request.getParameter("cuentaDestino"); // puede ser null

        String estadoFinal = "";

        try {
            double saldoAntes = obtenerSaldo(cuenta);
            boolean ok = false;

            if ("003".equals(tipo)) {
                ok = registrar(BASE_URL + "/coreBancario/deposito", cuenta, importe);
            } else if ("004".equals(tipo)) {
                ok = registrar(BASE_URL + "/coreBancario/retiro", cuenta, importe);
            } else if ("008".equals(tipo)) {
                if (destino == null || destino.isEmpty()) {
                    estadoFinal = "{\"estado\":0,\"error\":\"Cuenta destino requerida\"}";
                    reenviar(request, response, estadoFinal);
                    return;
                }
                ok = registrarTransferencia(cuenta, destino, importe);
            } else {
                estadoFinal = "{\"estado\":0,\"error\":\"Tipo inválido\"}";
                reenviar(request, response, estadoFinal);
                return;
            }

            if (ok) {
                double saldoDespues = obtenerSaldo(cuenta);
                estadoFinal = "{\"estado\":1, \"mensaje\":\"Movimiento exitoso.\", " +
                              "\"saldoAntes\":" + saldoAntes + ", \"saldoDespues\":" + saldoDespues + "}";
            } else {
                estadoFinal = "{\"estado\":0, \"error\":\"Falló la operación.\"}";
            }

        } catch (Exception e) {
            estadoFinal = "{\"estado\":0, \"error\":\"" + e.getMessage().replace("\"", "'") + "\"}";
        }

        reenviar(request, response, estadoFinal);
    }

    private void reenviar(HttpServletRequest request, HttpServletResponse response, String estadoJson)
            throws ServletException, IOException {
        request.setAttribute("estado", estadoJson);
        request.getRequestDispatcher("deposito.jsp").forward(request, response);
    }

    private boolean registrar(String urlStr, String cuenta, String importe) throws IOException {
        String params = "cuenta=" + URLEncoder.encode(cuenta, "UTF-8") +
                        "&importe=" + URLEncoder.encode(importe, "UTF-8");

        HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = con.getOutputStream()) {
            os.write(params.getBytes());
        }

        return con.getResponseCode() == 200;
    }

    private boolean registrarTransferencia(String origen, String destino, String importe) throws IOException {
        String params = "cuentaOrigen=" + URLEncoder.encode(origen, "UTF-8") +
                        "&cuentaDestino=" + URLEncoder.encode(destino, "UTF-8") +
                        "&importe=" + URLEncoder.encode(importe, "UTF-8");

        HttpURLConnection con = (HttpURLConnection) new URL(BASE_URL + "/coreBancario/transferencia").openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = con.getOutputStream()) {
            os.write(params.getBytes());
        }

        return con.getResponseCode() == 200;
    }

    private double obtenerSaldo(String cuenta) throws IOException {
        URL url = new URL(BASE_URL + "/coreBancario/movimientos/" + cuenta);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            json.append(line);
        }
        br.close();

        // Parseo simple de movimientos
        String data = json.toString().replace("[", "").replace("]", "").replace("},{", "}@@{");
        String[] movimientos = data.split("@@");

        double saldo = 0.0;
        for (String mov : movimientos) {
            String acc = getJsonValue(mov, "accion");
            String impStr = getJsonValue(mov, "importe");
            if (impStr == null || impStr.isEmpty()) continue;

            double imp = Double.parseDouble(impStr);
            if ("INGRESO".equalsIgnoreCase(acc)) {
                saldo += imp;
            } else {
                saldo -= imp;
            }
        }

        return Math.round(saldo * 100.0) / 100.0;
    }

    private String getJsonValue(String json, String campo) {
        int i = json.indexOf("\"" + campo + "\":");
        if (i == -1) return "";
        int start = json.indexOf(":", i) + 1;
        int end = json.indexOf(",", start);
        if (end == -1) end = json.indexOf("}", start);
        return json.substring(start, end).replaceAll("\"", "").trim();
    }
}
