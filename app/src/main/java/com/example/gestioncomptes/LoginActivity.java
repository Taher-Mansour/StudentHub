
package com.example.gestioncomptes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestioncomptes.models.User;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        // Bouton de connexion
        btnLogin.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,
                        "Veuillez remplir tous les champs",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            boolean utilisateurTrouve = false;

            // Parcourir la liste des utilisateurs
            for (User user : UserManager.users) {

                if (user.getEmail().equalsIgnoreCase(email)
                        && user.getPassword().equals(password)) {

                    utilisateurTrouve = true;
                    break;
                }
            }

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
        });

        // Navigation vers l'inscription
        tvCreateAccount.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );

            startActivity(intent);
        });
    }
}
