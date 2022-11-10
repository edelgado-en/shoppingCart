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
}
