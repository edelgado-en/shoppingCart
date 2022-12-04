package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserTest class in charge of testing the User class.
 *
 * @author Enrique Delgado
 */
public class UserTest {

    @Test
    public void testUser() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void testUserConstructor() {
        User user = new User("username", "password", false);
        assertNotNull(user);
    }

    @Test
    public void testGetUsername() {
        User user = new User("username", "password", false);
        assertEquals("username", user.getUsername());
    }

}
