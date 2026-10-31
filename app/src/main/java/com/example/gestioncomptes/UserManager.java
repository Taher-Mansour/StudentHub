
package com.example.gestioncomptes;

import com.example.gestioncomptes.models.User;
import java.util.ArrayList;

public class UserManager {

    public static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User(
                "Mansour",
                "Taher",
                "taher@gmail.com",
                "12345678"
        ));
    }

    public static boolean emailExiste(String email) {

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    public static void ajouterUser(User user) {
        users.add(user);
    }
}
