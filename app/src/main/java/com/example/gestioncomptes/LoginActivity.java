
package com.example.gestioncomptes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvCreateAccount;

    // Gestion de la base de données
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialisation de DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialisation des composants
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        // Bouton de connexion
        btnLogin.setOnClickListener(v -> verifierConnexion());

        // Navigation vers l'inscription
        tvCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );

            startActivity(intent);
        });
    }

    private void verifierConnexion() {

        // Récupération des informations
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        // Vérification des champs
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this,
                    "Veuillez remplir tous les champs",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        boolean utilisateurTrouve =
                databaseHelper.verifierConnexion(email, password);

        if (utilisateurTrouve) {

            Toast.makeText(this,
                    "Connexion réussie",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(
                    LoginActivity.this,
                    HomeActivity.class
            );

            startActivity(intent);
            finish();

        } else {

            Toast.makeText(this,
                    "Email ou mot de passe incorrect",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
