
package com.example.gestioncomptes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            Toast.makeText(this,
                    "Déconnexion réussie",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(
                    HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
