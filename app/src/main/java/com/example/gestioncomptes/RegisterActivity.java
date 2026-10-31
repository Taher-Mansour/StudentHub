
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

    // Objet pour gérer la base de données SQLite
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialisation de DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialisation des composants
        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        // Bouton inscription
        btnRegister.setOnClickListener(v -> verifierInscription());

        // Retour à la connexion
        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(
                    RegisterActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
            finish();
        });
    }

    private void verifierInscription() {

        // Récupération des données
        String nom = etNom.getText().toString().trim();
        String prenom = etPrenom.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmation = etConfirmPassword.getText().toString();

        // Vérification du nom
        if (nom.isEmpty()) {
            etNom.setError("Le nom est obligatoire");
            etNom.requestFocus();
            return;
        }

        // Vérification du prénom
        if (prenom.isEmpty()) {
            etPrenom.setError("Le prénom est obligatoire");
            etPrenom.requestFocus();
            return;
        }

        // Vérification de l'email
        if (email.isEmpty()) {
            etEmail.setError("L'e-mail est obligatoire");
            etEmail.requestFocus();
            return;
        }

        // Vérification du mot de passe
        if (password.isEmpty()) {
            etPassword.setError("Le mot de passe est obligatoire");
            etPassword.requestFocus();
            return;
        }

        // Vérification de la confirmation
        if (confirmation.isEmpty()) {
            etConfirmPassword.setError("Confirmez votre mot de passe");
            etConfirmPassword.requestFocus();
            return;
        }

        // Vérification du format de l'email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Adresse e-mail invalide");
            etEmail.requestFocus();
            return;
        }

        // Vérification de la longueur du mot de passe
        if (password.length() < 8) {
            etPassword.setError("Minimum 8 caractères");
            etPassword.requestFocus();
            return;
        }

        // Vérification de la confirmation du mot de passe
        if (!password.equals(confirmation)) {
            etConfirmPassword.setError(
                    "Les mots de passe ne correspondent pas"
            );
            etConfirmPassword.requestFocus();
            return;
        }

        // Vérifier si l'email existe déjà dans SQLite
        if (databaseHelper.emailExiste(email)) {
            etEmail.setError("Cet e-mail est déjà utilisé");
            etEmail.requestFocus();
            return;
        }

        // Insérer l'utilisateur dans SQLite
        boolean resultat = databaseHelper.ajouterUtilisateur(
                nom,
                prenom,
                email,
                password
        );

        if (resultat) {

            Toast.makeText(this,
                    "Inscription réussie !",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(
                    RegisterActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
            finish();

        } else {

            Toast.makeText(this,
                    "Erreur lors de l'inscription",
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
