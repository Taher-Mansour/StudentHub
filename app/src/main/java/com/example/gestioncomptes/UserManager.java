
package com.example.gestioncomptes;

import android.content.Context;

public class UserManager {

    private static DatabaseHelper databaseHelper;

    // Initialiser la base de données
    public static void initialiser(Context context) {

        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(
                    context.getApplicationContext()
            );
        }
    }

    // Vérifier si l'email existe
    public static boolean emailExiste(String email) {

        return databaseHelper.emailExiste(email);
    }

    // Ajouter un utilisateur
    public static boolean ajouterUser(String nom, String prenom,
                                      String email, String password) {

        return databaseHelper.ajouterUtilisateur(
                nom,
                prenom,
                email,
                password
        );
    }

    // Vérifier les informations de connexion
    public static boolean verifierConnexion(String email,
                                            String password) {

        return databaseHelper.verifierConnexion(email, password);
    }

    // Fermer la base de données
    public static void fermer() {

        if (databaseHelper != null) {
            databaseHelper.close();
            databaseHelper = null;
        }
    }
}
