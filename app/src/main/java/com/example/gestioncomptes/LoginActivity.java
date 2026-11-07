
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

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialisation de UserManager
        UserManager.initialiser(this);

        // Initialisation de DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialisation des composants
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        // Bouton connexion
        btnLogin.setOnClickListener(v -> verifierConnexion());

        // Navigation vers inscription
        tvCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );

            startActivity(intent);
        });
    }

    private void verifierConnexion() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        // Vérification des champs
        if (email.isEmpty() || password.isEmpty()) {

            Toast.makeText(this,
                    "Veuillez remplir tous les champs",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        // Vérification avec UserManager
        boolean utilisateurTrouve =
                UserManager.verifierConnexion(email, password);

        if (utilisateurTrouve) {

            // Récupérer l'identifiant de l'utilisateur
            int userId = databaseHelper.getUserId(email);

            if (userId == -1) {
                Toast.makeText(this,
                        "Impossible de récupérer l'utilisateur",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this,
                    "Connexion réussie",
                    Toast.LENGTH_SHORT).show();

            // Ouvrir HomeActivity
            Intent intent = new Intent(
                    LoginActivity.this,
                    HomeActivity.class
            );

            intent.putExtra("USER_ID", userId);
            intent.putExtra("USER_EMAIL", email);

            startActivity(intent);
            finish();

        } else {

            Toast.makeText(this,
                    "Email ou mot de passe incorrect",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
