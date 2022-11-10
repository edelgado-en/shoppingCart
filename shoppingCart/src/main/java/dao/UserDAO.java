package dao;

import models.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserDAO implements PersistenceService<User> {

    @Override
    public void save(User user) {
        // save user object to a file name users.txt
        try {
            FileOutputStream f = new FileOutputStream(new File("users.txt"));
            ObjectOutputStream output = new ObjectOutputStream(f);

            //write object to file
            output.writeObject(user);

            //close streams. TODO: change this try with resources
            output.close();
            f.close();


        } catch (Exception e) {

        }





    }

    @Override
    public User load(User user) {
        // Read the user from the file system under users folder
        //Use the username as a file name
        //Use the password as the value
        //return the user object

        //return null if the user already exists and handle it at the controller level




        return null;
    }
}
