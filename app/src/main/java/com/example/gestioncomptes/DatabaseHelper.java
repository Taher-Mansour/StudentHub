package com.example.gestioncomptes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import org.mindrot.jbcrypt.BCrypt;
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gestion_comptes.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT NOT NULL, " +
                "prenom TEXT NOT NULL, " +
                "email TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL" +
                ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users");

        onCreate(db);
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
}