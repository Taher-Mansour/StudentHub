
package com.example.gestioncomptes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView navHome;
    private TextView navMatieres;
    private TextView navTaches;
    private TextView navProfil;

    private TextView tvVoirMatieres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialisation des composants
        initialiserComposants();

        // Gestion de la navigation
        configurerNavigation();
    }

    // Initialiser les composants
    private void initialiserComposants() {

        navHome = findViewById(R.id.navHome);
        navMatieres = findViewById(R.id.navMatieres);
        navTaches = findViewById(R.id.navTaches);
        navProfil = findViewById(R.id.navProfil);

        tvVoirMatieres = findViewById(R.id.tvVoirMatieres);
    }

    // Configurer les boutons de navigation
    private void configurerNavigation() {

        // Accueil
        navHome.setOnClickListener(v -> {
            Toast.makeText(this,
                    "Vous êtes déjà sur l'accueil",
                    Toast.LENGTH_SHORT).show();
        });

        // Matières
        navMatieres.setOnClickListener(v -> {
            Toast.makeText(this,
                    "Section Mes matières",
                    Toast.LENGTH_SHORT).show();
        });

        // Tâches
        navTaches.setOnClickListener(v -> {
            Toast.makeText(this,
                    "Section Mes tâches",
                    Toast.LENGTH_SHORT).show();
        });

        // Profil
        navProfil.setOnClickListener(v -> {
            Toast.makeText(this,
                    "Section Profil",
                    Toast.LENGTH_SHORT).show();
        });

        // Voir toutes les matières
        tvVoirMatieres.setOnClickListener(v -> {
            Toast.makeText(this,
                    "Afficher toutes les matières",
                    Toast.LENGTH_SHORT).show();
        });
    }
}
