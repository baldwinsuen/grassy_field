package com.naar.data;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

//README!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//Comment these out TeamDaoImpl
//teamPokemonDao.deleteTeamPokemonbyTeamId(id);
//typeStatDao.deleteTypeStatByTeam(id);
//baseStatDao.deleteBaseStatByTeam(id);

@RunWith(SpringRunner.class)
@SpringBootTest
class TeamDaoImplTest {

    @Autowired
    UserDao userdao;
    @Autowired
    TeamDao teamdao;

    @BeforeEach
    public void setUp() {
        List<User> users = userdao.getAllUsers();
        for(User user : users) {
            userdao.deleteUserbyId(user.getId());
        }
    }



    @Test
    void addGetTeam() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);

        Team team1 = new Team();
        team1.setName("My First Team");
        team1.setUser(user1);
        team1 = teamdao.addTeam(team1);

        Team fromdao = teamdao.getTeamById(team1.getId());

        assertEquals(team1,fromdao);

    }

    @Test
    void updateTeam() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);

        Team team1 = new Team();
        team1.setName("My First Team");
        team1.setUser(user1);
        team1 = teamdao.addTeam(team1);
        Team temp = new Team();
        temp.setName("TEMP");
        temp.setUser(user1);
        temp.setId(team1.getId());
        teamdao.updateTeam(temp);

        team1 = teamdao.getTeamById(temp.getId());

        assertEquals(team1,temp);


    }

    @Test
    void getTeamsOfUser() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);

        Team team1 = new Team();
        team1.setName("My First Team");
        team1.setUser(user1);
        team1 = teamdao.addTeam(team1);
        Team team2 = new Team();
        team2.setName("My Second Team");
        team2.setUser(user1);
        team2 = teamdao.addTeam(team2);

        List<Team> fromdao = teamdao.getTeamsOfUser(user1.getId());

        assertEquals(2, fromdao.size());
        assertTrue(fromdao.contains(team1));
        assertTrue(fromdao.contains(team2));


    }



    @Test
    void deleteTeamById() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);

        Team team1 = new Team();
        team1.setName("My First Team");
        team1.setUser(user1);
        team1 = teamdao.addTeam(team1);

        userdao.deleteUserbyId(user1.getId());
        team1 = teamdao.getTeamById(team1.getId());
        assertNull(team1);
    }
}