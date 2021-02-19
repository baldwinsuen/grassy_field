package com.naar.service;

import com.naar.data.UserDao;
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
class UserServiceTest {

    @Autowired
    UserService userservice;

    @Autowired
    UserDao userdao;

    @BeforeEach
    public void setUp() {
        List<User> users = userdao.getAllUsers();
        for(User user : users) {
            userservice.deleteUserById(user.getId());
        }
    }


    @Test
    void createGetUser() {
        User user = userservice.createUser("Test");
        User fromservice = userservice.findUser(user.getId());

        assertEquals(user, fromservice);
    }

    @Test
    void updateUserById() {
        User user = userservice.createUser("Test");
        boolean updateTest = userservice.updateUserById("Test1",user.getId());
        assertTrue(updateTest);


        User newuser = userservice.findUser(user.getId());
        assertNotEquals(user,newuser);


    }

    @Test
    void deleteUserById() {
        User user = userservice.createUser("Test");
        boolean deleteTest = userservice.deleteUserById(user.getId());
        assertTrue(deleteTest);

        user = userservice.findUser(user.getId());
        assertNull(user);
    }


}