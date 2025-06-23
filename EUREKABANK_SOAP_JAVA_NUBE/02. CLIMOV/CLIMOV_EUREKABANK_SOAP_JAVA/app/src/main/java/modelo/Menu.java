package modelo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.climov_eurekabank_soap_java.R;

public class Menu extends AppCompatActivity {
    Button btnDepositos, btnMovimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu); // Usa directamente el nombre del layout

        btnDepositos = findViewById(R.id.btn_deposito);
        btnMovimientos = findViewById(R.id.btn_movimientos);

        btnDepositos.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, Depositos.class);
            startActivity(intent);
        });

        btnMovimientos.setOnClickListener(v -> {
            // Aquí corregimos para que vaya a MovimientoTabla, no MovimientoActivity
            Intent intent = new Intent(Menu.this, MovimientoTabla.class);
            startActivity(intent);
        });
    }
}
