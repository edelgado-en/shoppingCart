package services;

import dao.UserDAO;
import models.User;

/**
 * User service class that provides the business logic for saving and loading users.
 *
 * @author Enrique Delgado
 */
public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void saveUser(String username, String password) {
        userDAO.save(new User(username, password));
    }

    public User loadUser(String username, String password) {
        return userDAO.load(new User(username, password));
    }
}
