package ec.edu.eureka_bank_climov_restful.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import ec.edu.eureka_bank_climov_restful.R;
import ec.edu.eureka_bank_climov_restful.api.ApiService;
import ec.edu.eureka_bank_climov_restful.api.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroMovimientoActivity extends AppCompatActivity {

    private EditText txtCuentaOrigen, txtCuentaDestino, txtImporte;
    private Spinner spnTipo;
    private Button btnRegistrar;
    private TextView txtResultado;
    private ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_movimiento);

        txtCuentaOrigen = findViewById(R.id.txtCuentaOrigen);
        txtCuentaDestino = findViewById(R.id.txtCuentaDestino);
        txtImporte = findViewById(R.id.txtImporte);
        spnTipo = findViewById(R.id.spnTipo);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        txtResultado = findViewById(R.id.txtResultado);
        api = RetrofitClient.getApiService();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_movimiento, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipo.setAdapter(adapter);

        spnTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tipo = parent.getItemAtPosition(position).toString();
                txtCuentaDestino.setVisibility(tipo.equals("TRANSFERENCIA") ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnRegistrar.setOnClickListener(v -> registrarMovimiento());
    }

    private void registrarMovimiento() {
        String cuentaOrigen = txtCuentaOrigen.getText().toString().trim();
        String cuentaDestino = txtCuentaDestino.getText().toString().trim();
        String tipo = spnTipo.getSelectedItem().toString();
        String importeStr = txtImporte.getText().toString().trim();

        if (cuentaOrigen.isEmpty() || importeStr.isEmpty()) {
            txtResultado.setText("❗ Todos los campos son obligatorios.");
            return;
        }

        if (tipo.equals("TRANSFERENCIA") && cuentaDestino.isEmpty()) {
            txtResultado.setText("❗ Ingrese la cuenta destino.");
            return;
        }

        double importe = Double.parseDouble(importeStr);

        Call<ResponseBody> call;

        switch (tipo) {
            case "DEPÓSITO":
                call = api.registrarDeposito(cuentaOrigen, importe);
                break;
            case "RETIRO":
                call = api.registrarRetiro(cuentaOrigen, importe);
                break;
            case "TRANSFERENCIA":
                call = api.registrarTransferencia(cuentaOrigen, cuentaDestino, importe);
                break;
            default:
                txtResultado.setText("❌ Tipo de movimiento inválido.");
                return;
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegistroMovimientoActivity.this, "✅ Movimiento registrado correctamente.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegistroMovimientoActivity.this, MenuActivity.class));
                    finish();
                } else {
                    txtResultado.setText("❌ Error del servidor al registrar movimiento.");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                txtResultado.setText("⚠️ Fallo de conexión: " + t.getMessage());
            }
        });
    }
}
