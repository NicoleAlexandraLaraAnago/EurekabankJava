package modelo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.climov_eurekabank_soap_java.R;

public class Depositos extends AppCompatActivity {

    EditText etCuenta, etCodigo, etMonto;
    Button btnEnviar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.climov_eurekabank_soap_java.R.layout.activity_depositos);

        etCuenta = findViewById(R.id.editCuenta);
        etCodigo = findViewById(R.id.et_contrasena);
        etMonto = findViewById(R.id.editMonto);
        btnEnviar = findViewById(R.id.btnRealizarDeposito);

        btnEnviar.setOnClickListener(v -> {
            String cuenta = etCuenta.getText().toString();
            String codigo = etCodigo.getText().toString();
            String monto = etMonto.getText().toString();

            WSEurekabankCliente cliente = new WSEurekabankCliente();
            String resultado = cliente.regDeposito(cuenta, codigo, monto);

            Toast.makeText(Depositos.this, resultado, Toast.LENGTH_SHORT).show();
        });
    }
}
