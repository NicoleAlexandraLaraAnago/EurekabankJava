// MainActivity.java
package modelo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.climov_eurekabank_soap_java.R;

public class MainActivity extends AppCompatActivity {
    private EditText etUsuario, etContrasena;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = findViewById(R.id.et_usuario);
        etContrasena = findViewById(R.id.et_contrasena);
        btnIngresar = findViewById(R.id.btn_ingresar);

        btnIngresar.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString();
            String contrasena = etContrasena.getText().toString();

            WSLoginClient loginClient = new WSLoginClient();
            loginClient.login(usuario, contrasena, new WSLoginClient.LoginCallback() {
                @Override
                public void onLoginSuccess(String message) {
                    runOnUiThread(() -> {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, Menu.class);
                        intent.putExtra("cuenta", usuario);
                        startActivity(intent);
                    });
                }

                @Override
                public void onLoginError(String error) {
                    runOnUiThread(() -> {
                        Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
                    });
                }
            });
        });
    }
}
