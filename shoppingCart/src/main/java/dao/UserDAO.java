package dao;

import models.User;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class UserDAO implements PersistenceService<User> {

    @Override
    public void save(User user) {
        // save user object to a file name users.txt
        try {
            //set UTF-8 encoding
            //TODO: change this to try with resources
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("users.txt", true), StandardCharsets.UTF_8);
            writer.write(user.toString());
            writer.write('\n');

            writer.close();

        } catch (Exception e) {

        }
    }

    @Override
    public User load(User user) {
        //read all users from the users.text. If you find the provided user, then return it. Otherwise return null.
        // loop through all entries until you find the user
        // if you find the user, return it
        // if you don't find the user, return null
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // process the line
                    if (line.contains(user.getUsername())) {
                        return user;
                    }
                }
            }

        } catch (Exception e) {

        }

        return null;
    }
}
