package ec.edu.eureka_bank_climov_restful.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.eureka_bank_climov_restful.R;
import ec.edu.eureka_bank_climov_restful.api.ApiService;
import ec.edu.eureka_bank_climov_restful.api.RetrofitClient;
import ec.edu.eureka_bank_climov_restful.model.Cuenta;
import ec.edu.eureka_bank_climov_restful.model.Movimiento;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovimientosActivity extends AppCompatActivity {

    private EditText txtCuenta;
    private Button btnBuscar;
    private TextView txtSaldo, txtLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);

        txtCuenta = findViewById(R.id.txtCuenta);
        btnBuscar = findViewById(R.id.btnBuscar);
        txtSaldo = findViewById(R.id.txtSaldo);
        txtLista = findViewById(R.id.txtLista);

        btnBuscar.setOnClickListener(v -> {
            String cuenta = txtCuenta.getText().toString().trim();

            if (!cuenta.isEmpty()) {
                consultarSaldo(cuenta);
                consultarMovimientos(cuenta);
            }
        });
    }

    private void consultarSaldo(String cuenta) {
        RetrofitClient.getApiService().obtenerCuenta(cuenta).enqueue(new Callback<Cuenta>() {
            @Override
            public void onResponse(Call<Cuenta> call, Response<Cuenta> response) {
                if (response.isSuccessful() && response.body() != null) {
                    txtSaldo.setText("Saldo: $" + response.body().getSaldo());
                } else {
                    txtSaldo.setText("Saldo no disponible");
                }
            }

            @Override
            public void onFailure(Call<Cuenta> call, Throwable t) {
                txtSaldo.setText("Error al obtener saldo");
            }
        });
    }

    private void consultarMovimientos(String cuenta) {
        RetrofitClient.getApiService().obtenerMovimientos(cuenta).enqueue(new Callback<List<Movimiento>>() {
            @Override
            public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    StringBuilder builder = new StringBuilder();
                    for (Movimiento m : response.body()) {
                        builder.append("🗓 Fecha: ").append(m.getFecha().split("T")[0]).append("\n")
                                .append("💳 Movimiento: ").append(m.getTipo()).append("\n")
                                .append("🔄 Acción: ").append(m.getAccion()).append("\n")
                                .append("💰 Monto: $").append(m.getImporte()).append("\n")
                                .append("------------------------------\n");
                    }
                    txtLista.setText(builder.toString());
                } else {
                    txtLista.setText("No hay movimientos");
                }
            }

            @Override
            public void onFailure(Call<List<Movimiento>> call, Throwable t) {
                txtLista.setText("Error al obtener movimientos");
            }
        });
    }
}
