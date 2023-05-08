package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        // Step 1
        userService.createUsersTable();
        // Step 2
        userService.saveUser("Stanislav", "Baretskiy", (byte) 51);
        userService.saveUser("Eugeniy", "Ponasenkov", (byte) 41);
        userService.saveUser("Pasha", "Technick", (byte) 38);
        userService.saveUser("Bogdan", "Titomir", (byte) 56);
        // Step 3
        userService.getAllUsers().forEach(System.out::println);
        // Step 4
        userService.cleanUsersTable();
        // Step 5
        userService.dropUsersTable();
    }
}
