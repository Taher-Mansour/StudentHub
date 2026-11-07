
package com.example.gestioncomptes;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gestioncomptes.models.Subject;

import org.mindrot.jbcrypt.BCrypt;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gestion_comptes.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Création des tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Table des utilisateurs
        String CREATE_USERS_TABLE = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT NOT NULL, " +
                "prenom TEXT NOT NULL, " +
                "email TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL" +
                ")";

        db.execSQL(CREATE_USERS_TABLE);

        // Table des matières
        createSubjectsTable(db);
    }

    // Création de la table subjects
    private void createSubjectsTable(SQLiteDatabase db) {

        String CREATE_SUBJECTS_TABLE = "CREATE TABLE IF NOT EXISTS subjects (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "code TEXT NOT NULL, " +
                "teacher TEXT, " +
                "description TEXT, " +
                "user_id INTEGER NOT NULL" +
                ")";

        db.execSQL(CREATE_SUBJECTS_TABLE);
    }

    // Mise à jour de la base de données
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {

        if (oldVersion < 2) {
            createSubjectsTable(db);
        }
    }

    public boolean ajouterUtilisateur(String nom, String prenom,
                                      String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        String passwordHash = BCrypt.hashpw(
                password,
                BCrypt.gensalt(10)
        );

        ContentValues values = new ContentValues();

        values.put("nom", nom);
        values.put("prenom", prenom);
        values.put("email", email);
        values.put("password", passwordHash);

        long resultat = db.insert("users", null, values);

        return resultat != -1;
    }

    // Vérifier si un email existe
    public boolean emailExiste(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "users",
                new String[]{"id"},
                "email = ?",
                new String[]{email},
                null,
                null,
                null
        );

        boolean existe = cursor.moveToFirst();

        cursor.close();

        return existe;
    }

    // Vérifier la connexion
    public boolean verifierConnexion(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "users",
                new String[]{"password"},
                "email = ?",
                new String[]{email},
                null,
                null,
                null
        );

        boolean valide = false;

        if (cursor.moveToFirst()) {

            String passwordHash = cursor.getString(0);

            valide = BCrypt.checkpw(password, passwordHash);
        }

        cursor.close();

        return valide;
    }

    // Récupérer l'identifiant d'un utilisateur à partir de son email
    public int getUserId(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "users",
                new String[]{"id"},
                "email = ?",
                new String[]{email},
                null,
                null,
                null
        );

        int userId = -1;

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }

        cursor.close();

        return userId;
    }

    // Ajouter une matière dans SQLite
    public boolean ajouterMatiere(Subject subject) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", subject.getName());
        values.put("code", subject.getCode());
        values.put("teacher", subject.getTeacher());
        values.put("description", subject.getDescription());
        values.put("user_id", subject.getUserId());

        long resultat = db.insert("subjects", null, values);

        return resultat != -1;
    }
}
