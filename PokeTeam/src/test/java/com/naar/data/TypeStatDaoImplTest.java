package com.naar.data;


import com.naar.model.TypeStat;
import com.naar.model.Team;
import com.naar.model.User;
import org.junit.jupiter.api.AfterEach;
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
class TypeStatDaoImplTest {

    @Autowired
    UserDao userdao;
    @Autowired
    TeamDao teamdao;
    @Autowired
    TypeStatDao typestatdao;

    @BeforeEach
    public void setUp() {
        List<User> users = userdao.getAllUsers();
        for(User user : users) {
            userdao.deleteUserbyId(user.getId());
        }
    }

    @Test
    void addGetTypeStatTeam() {
        User user1 = new User();
        user1.setName("Ray Sagastume");
        user1 = userdao.addUser(user1);

        Team team1 = new Team();
        team1.setName("My First Team");
        team1.setUser(user1);
        team1 = teamdao.addTeam(team1);

        TypeStat typestat1 = new TypeStat();
        String [] temps = typestat1.getTypes();
        for(String temp: temps){
            typestat1.setTypeStat(temp,2);
        }
        typestat1.setTeam(team1);
        typestat1 = typestatdao.addTypeStatToTeam(typestat1);

        TypeStat fromdao = typestatdao.getTypeStatsOfTeam(team1);

        assertEquals(typestat1,fromdao);
    }

    @Test
    void updateTypeStatOfTeam() {
        User user1 = new User();
        user1.setName("Ray Sagastume");
        user1 = userdao.addUser(user1);

        Team team1 = new Team();
        team1.setName("My First Team");
        team1.setUser(user1);
        team1 = teamdao.addTeam(team1);

        TypeStat typestat1 = new TypeStat();
        String [] temps = typestat1.getTypes();
        for(String temp: temps){
            typestat1.setTypeStat(temp,2);
        }
        typestat1.setTeam(team1);
        typestat1 = typestatdao.addTypeStatToTeam(typestat1);

        TypeStat typestat2 = new TypeStat();
        for(String temp: temps){
            typestat2.setTypeStat(temp,3);
        }
        typestat2.setTeam(team1);
        typestat2.setId(typestat1.getId());
        typestatdao.updateTypeStatOfTeam(typestat2);

        TypeStat fromdao = typestatdao.getTypeStatsOfTeam(team1);

        assertEquals(typestat2,fromdao);
        assertNotEquals(typestat1,fromdao);
    }

    @Test
    void deleteTypeStatByTeam() {
        User user1 = new User();
        user1.setName("Ray Sagastume");
        user1 = userdao.addUser(user1);

        Team team1 = new Team();
        team1.setName("My First Team");
        team1.setUser(user1);
        team1 = teamdao.addTeam(team1);

        TypeStat typestat1 = new TypeStat();
        String [] temps = typestat1.getTypes();
        for(String temp: temps){
            typestat1.setTypeStat(temp,2);
        }
        typestat1.setTeam(team1);
        typestat1 = typestatdao.addTypeStatToTeam(typestat1);
        userdao.deleteUserbyId(user1.getId());
        TypeStat fromdao = typestatdao.getTypeStatsOfTeam(team1);
        assertNull(fromdao);
    }
}
