package modelo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.climov_eurekabank_soap_java.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimientoTabla extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovimientoAdapter adapter;
    private List<Movimiento> listaMovimientos = new ArrayList<>();

    private EditText etCuenta;
    private Button btnConsultar;

    private static final String NAMESPACE = "http://ws.monster.edu.ec/";
    private static final String URL = "http://192.168.100.36:8080/WSEureka";
    private static final String METHOD_NAME = "traerMovimientos";
    private static final String SOAP_ACTION = NAMESPACE + METHOD_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_tabla);

        recyclerView = findViewById(R.id.recycler_movimientos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovimientoAdapter(listaMovimientos);
        recyclerView.setAdapter(adapter);

        etCuenta = findViewById(R.id.et_cuenta);
        btnConsultar = findViewById(R.id.btn_consultar);

        btnConsultar.setOnClickListener(v -> {
            String cuentaIngresada = etCuenta.getText().toString().trim();
            if (!cuentaIngresada.isEmpty()) {
                traerMovimientos(cuentaIngresada);
            } else {
                Toast.makeText(this, "Ingrese una cuenta válida", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void traerMovimientos(String cuenta) {
        listaMovimientos.clear();

        new Thread(() -> {
            try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("cuenta", cuenta);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                envelope.dotNet = false;

                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.call(SOAP_ACTION, envelope);

                Log.d("SOAP_RAW", "REQUEST: " + transport.requestDump);
                Log.d("SOAP_RAW", "RESPONSE: " + transport.responseDump);

                SoapObject response = (SoapObject) envelope.bodyIn;

                // Aquí imprimes toda la estructura de respuesta para depurar
                Log.d("SOAP_RESPONSE_DETALLE", response.toString());

                // Por ahora intenta obtener movimientos directamente de 'response'
                List<Movimiento> movimientos = parsearMovimientos(response);

                runOnUiThread(() -> {
                    if (movimientos.isEmpty()) {
                        Toast.makeText(this, "No se encontraron movimientos", Toast.LENGTH_SHORT).show();
                    } else {
                        listaMovimientos.addAll(movimientos);
                        adapter.notifyDataSetChanged();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "Error al consultar movimientos", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private List<Movimiento> parsearMovimientos(SoapObject response) {
        List<Movimiento> movimientos = new ArrayList<>();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        int count = response.getPropertyCount();

        for (int i = 0; i < count; i++) {
            Object obj = response.getProperty(i);

            if (obj instanceof SoapObject) {
                SoapObject movSoap = (SoapObject) obj;

                try {
                    String accion = movSoap.getPropertySafelyAsString("accion");
                    String cuenta = movSoap.getPropertySafelyAsString("cuenta");
                    String fechaStr = movSoap.getPropertySafelyAsString("fecha");
                    String tipo = movSoap.getPropertySafelyAsString("tipo");
                    String importeStr = movSoap.getPropertySafelyAsString("importe");
                    String nromovStr = movSoap.getPropertySafelyAsString("nromov");

                    Date fecha = formatoFecha.parse(fechaStr.substring(0, 10));
                    double importe = Double.parseDouble(importeStr);
                    int nromov = Integer.parseInt(nromovStr);

                    Movimiento movimiento = new Movimiento(cuenta, nromov, fecha, tipo, accion, importe);
                    movimientos.add(movimiento);
                } catch (Exception e) {
                    Log.e("PARSE_ERROR", "Error parseando movimiento: " + e.getMessage());
                }
            }
        }

        return movimientos;
    }
}
