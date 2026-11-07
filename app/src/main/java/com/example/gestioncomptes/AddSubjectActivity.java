
package com.example.gestioncomptes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestioncomptes.models.Subject;

public class AddSubjectActivity extends AppCompatActivity {

    private EditText etSubjectName;
    private EditText etSubjectCode;
    private EditText etSubjectTeacher;
    private EditText etSubjectDescription;

    private Button btnSaveSubject;

    private DatabaseHelper databaseHelper;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        // Récupérer les composants
        etSubjectName = findViewById(R.id.etSubjectName);
        etSubjectCode = findViewById(R.id.etSubjectCode);
        etSubjectTeacher = findViewById(R.id.etSubjectTeacher);
        etSubjectDescription = findViewById(R.id.etSubjectDescription);

        btnSaveSubject = findViewById(R.id.btnSaveSubject);

        // Initialiser la base de données
        databaseHelper = new DatabaseHelper(this);

        // Récupérer l'identifiant de l'utilisateur connecté
        userId = getIntent().getIntExtra("USER_ID", -1);

        if (userId == -1) {
            Toast.makeText(this,
                    "Utilisateur non identifié",
                    Toast.LENGTH_SHORT).show();

            finish();
            return;
        }

        // Enregistrer la matière
        btnSaveSubject.setOnClickListener(v -> ajouterMatiere());
    }

    private void ajouterMatiere() {

        String name = etSubjectName.getText().toString().trim();
        String code = etSubjectCode.getText().toString().trim();

        String teacher = etSubjectTeacher.getText().toString().trim();
        String description = etSubjectDescription.getText().toString().trim();

        // Vérification des champs obligatoires
        if (name.isEmpty()) {
            etSubjectName.setError("Le nom est obligatoire");
            etSubjectName.requestFocus();
            return;
        }

        if (code.isEmpty()) {
            etSubjectCode.setError("Le code est obligatoire");
            etSubjectCode.requestFocus();
            return;
        }

        // Créer l'objet Subject
        Subject subject = new Subject(
                name,
                code,
                teacher,
                description,
                userId
        );

        // Enregistrer dans SQLite
        boolean resultat = databaseHelper.ajouterMatiere(subject);

        if (resultat) {

            Toast.makeText(this,
                    "Matière ajoutée avec succès",
                    Toast.LENGTH_SHORT).show();

            finish();

        } else {

            Toast.makeText(this,
                    "Erreur lors de l'ajout de la matière",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
