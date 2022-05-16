package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Petrov", (byte) 30);
        userService.saveUser("Elena", "Sidorova", (byte) 25);
        userService.saveUser("Alexey", "Ivanov", (byte) 44);
        userService.saveUser("Alexandr", "Smirnov", (byte) 27);

        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
