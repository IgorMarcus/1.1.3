package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Bruce", "Wayne", (byte) 35);
        userService.saveUser("James", "Gordon", (byte) 50);
        userService.saveUser("Harvey", "Dent", (byte) 35);
        userService.saveUser("Lucius", "Fox", (byte) 60);

        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
