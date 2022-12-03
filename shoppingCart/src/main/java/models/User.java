package models;

import java.io.Serializable;

/**
 * User model class that represents a user. A user has a username, a password, and a role (buyer or seller).
 *
 * @author Enrique Delgado
 */
public class User implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * The username attribute.
     */
    private String username;

    /**
     * The password attribute.
     */
    private String password;

    /**
     * The role of the user. It can be either buyer or seller.
     */
    private boolean isSeller;

    /**
     * User constructor. Empty constructor is needed for serialization.
     */
    public User() {
    }

    /**
     * Instantiates a new User with the provider username and password.
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Instantiates a new User with the provided username, password, and role.
     *
     * @param name
     * @param password
     * @param isSeller
     */
    public User(String name, String password, boolean isSeller) {
        this.username = name;
        this.password = password;
        this.isSeller = isSeller;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User user = (User) obj;

        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return username.hashCode() + password.hashCode();
    }

}
