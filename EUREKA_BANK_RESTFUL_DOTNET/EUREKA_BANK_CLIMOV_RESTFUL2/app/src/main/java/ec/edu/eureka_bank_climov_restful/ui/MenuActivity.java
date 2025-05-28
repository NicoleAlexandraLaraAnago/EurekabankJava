package ec.edu.eureka_bank_climov_restful.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.eureka_bank_climov_restful.R;

public class MenuActivity extends AppCompatActivity {

    private Button btnMovimientos, btnRegistrar, btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMovimientos = findViewById(R.id.btnMovimientos);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnCerrar = findViewById(R.id.btnCerrar);

        btnMovimientos.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, MovimientosActivity.class));
        });

        btnRegistrar.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, RegistroMovimientoActivity.class));
        });

        btnCerrar.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, LoginActivity.class));
            finish();
        });
    }
}
