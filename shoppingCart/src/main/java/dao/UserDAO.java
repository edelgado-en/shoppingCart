package dao;

import models.User;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * UserDAO is a class that implements the PersistenceService interface.
 * This class is responsible for saving and loading users to the file system.
 *
 * @author Enrique Delgado
 */
public class UserDAO implements PersistenceService<User> {

    private static final String FILE_NAME = "users.xml";

    @Override
    public void save(User user) {
        //add user to the list of users in users.xml using XMLEncoder
        ArrayList<User> userList = new ArrayList<>();
        User loadedUser = null;
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FILE_NAME)));
            userList = (ArrayList<User>) decoder.readObject();
            decoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        userList.add(user);

        //write the new list of users to the file
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("users.xml");
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(userList);
            encoder.close();
            out.close();

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override
    public User load(User user) {
        // read user from users.xml using XMLDecoder
        // return the user object
        ArrayList<User> userList = new ArrayList<>();
        User loadedUser = null;
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FILE_NAME)));
            userList = (ArrayList<User>) decoder.readObject();
            decoder.close();

            for (User u : userList) {
                if (u.getUsername().equals(user.getUsername())) {
                    loadedUser = u;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loadedUser;
    }
}
