package com.naar.data;


import com.naar.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDaoImplTest {
    @Autowired
    UserDao userdao;

    @BeforeEach
    public void setUp() {
        List<User> users = userdao.getAllUsers();
        for(User user : users) {
            System.out.println("TEST");
            userdao.deleteUserbyId(user.getId());
        }
    }


    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);
        User user2 = new User();
        user2.setName("Collin Naar");
        user2 = userdao.addUser(user2);

        List<User> fromdao = userdao.getAllUsers();

        assertEquals(2, fromdao.size());
        assertTrue(fromdao.contains(user1));
        assertTrue(fromdao.contains(user2));
    }


    @Test
    void addGETUser() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);


        User fromdao = userdao.getUserById(user1.getId());
        assertEquals(user1,fromdao);
    }

    @Test
    void updateUser() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);

        User usertemp = new User();
        usertemp.setName("Collin Naar");
        usertemp.setId(user1.getId());
        userdao.updateUser(usertemp);
        user1 = userdao.getUserById(user1.getId());

        assertEquals(user1,usertemp);

    }

    @Test
    void deleteUserbyId() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);

        userdao.deleteUserbyId(user1.getId());
        user1 = userdao.getUserById(user1.getId());
        assertNull(user1);
    }
}
