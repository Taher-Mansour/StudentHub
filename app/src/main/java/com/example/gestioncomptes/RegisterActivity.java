package com.example.gestioncomptes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNom, etPrenom, etEmail;
    private EditText etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(v -> verifierInscription());

        tvLogin.setOnClickListener(v -> {
            startActivity(
                    new Intent(RegisterActivity.this, LoginActivity.class)
            );
            finish();
        });
    }

    private void verifierInscription() {

        String nom = etNom.getText().toString().trim();
        String prenom = etPrenom.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmation =
                etConfirmPassword.getText().toString();

        if (nom.isEmpty()) {
            etNom.setError("Le nom est obligatoire");
            etNom.requestFocus();
            return;
        }

        if (prenom.isEmpty()) {
            etPrenom.setError("Le prénom est obligatoire");
            etPrenom.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("L'e-mail est obligatoire");
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Le mot de passe est obligatoire");
            etPassword.requestFocus();
            return;
        }

        if (confirmation.isEmpty()) {
            etConfirmPassword.setError(
                    "Confirmez votre mot de passe"
            );
            etConfirmPassword.requestFocus();
            return;
        }

        // 2. Vérifier le format de l'e-mail
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Adresse e-mail invalide");
            etEmail.requestFocus();
            return;
        }

        // 3. Vérifier la longueur du mot de passe
        if (password.length() < 8) {
            etPassword.setError(
                    "Minimum 8 caractères"
            );
            etPassword.requestFocus();
            return;
        }

        // 4. Vérifier la confirmation
        if (!password.equals(confirmation)) {
            etConfirmPassword.setError(
                    "Les mots de passe ne correspondent pas"
            );
            etConfirmPassword.requestFocus();
            return;
        }

        // Toutes les vérifications sont réussies
        Toast.makeText(
                this,
                "Les données sont valides !",
                Toast.LENGTH_SHORT
        ).show();
    }
}
