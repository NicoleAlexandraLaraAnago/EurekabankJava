package ec.edu.eureka_bank_climov_restful.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.eureka_bank_climov_restful.R;
import ec.edu.eureka_bank_climov_restful.api.ApiService;
import ec.edu.eureka_bank_climov_restful.api.RetrofitClient;
import ec.edu.eureka_bank_climov_restful.model.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario, edtClave;
    private Button btnLogin;
    private ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtClave = findViewById(R.id.edtClave);
        btnLogin = findViewById(R.id.btnLogin);
        api = RetrofitClient.getApiService();

        btnLogin.setOnClickListener(v -> {
            String usuario = edtUsuario.getText().toString().trim();
            String clave = edtClave.getText().toString().trim();

            if (usuario.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (usuario.equals("MONSTER") && clave.equals("MONSTER9")) {
                startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
