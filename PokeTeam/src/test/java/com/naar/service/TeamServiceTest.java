package com.naar.service;

import com.naar.data.UserDao;
import com.naar.model.Team;
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
class TeamServiceTest {

    @Autowired
    UserDao userdao;

    @Autowired
    UserService userservice;

    @Autowired
    TeamService teamservice;


    @BeforeEach
    public void setUp() {
        List<User> users = userdao.getAllUsers();
        for(User user : users) {
            userservice.deleteUserById(user.getId());
        }
    }

    @Test
    void getTeamsForUser() {
        User user = userservice.createUser("Douglas Schoedl");
        Team team1 = teamservice.createTeamOfUser("team1",user);
        Team team2 = teamservice.createTeamOfUser("team2",user);

        List<Team> fromservice = teamservice.getTeamsForUser(user.getId());
        assertEquals(fromservice.size(),2);
        assertTrue(fromservice.contains(team1));
        assertTrue(fromservice.contains(team2));
    }

    @Test
    void createGetTeamOfUser() {
        User user = userservice.createUser("Douglas Schoedl");
        Team team = teamservice.createTeamOfUser("team1",user);

        Team fromservice = teamservice.findTeam(team.getId());

        assertEquals(team,fromservice);
    }

    @Test
    void updateTeamById() {
        User user = userservice.createUser("Douglas Schoedl");
        Team team = teamservice.createTeamOfUser("team1",user);
        boolean updateTest = teamservice.updateTeamById("Newteam1",team.getId());
        assertTrue(updateTest);

        Team newteam = teamservice.findTeam(team.getId());
        assertNotEquals(team,newteam);
    }

    @Test
    void deleteTeamById() {
        User user = userservice.createUser("Douglas Schoedl");
        Team team = teamservice.createTeamOfUser("team1",user);
        boolean deleteTest = teamservice.deleteTeamById(team.getId());
        assertTrue(deleteTest);

        team = teamservice.findTeam(team.getId());
        assertNull(team);

    }


}