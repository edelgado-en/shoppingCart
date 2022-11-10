package models;

import java.io.Serializable;

public class User implements Serializable {

    public static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return "(" + username + ", " + password + ")";
    }
}
