package com.naar.data;


import com.naar.model.BaseStat;
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



//Comment these out in deleteTeamById in TeamDaoImpl for this to work
//teamPokemonDao.deleteTeamPokemonbyTeamId(id);
//typeStatDao.deleteTypeStatByTeam(id);
@RunWith(SpringRunner.class)
@SpringBootTest
class BaseStatDaoImplTest {

    @Autowired
    UserDao userdao;
    @Autowired
    TeamDao teamdao;
    @Autowired
    BaseStatDao basestatdao;

    @BeforeEach
    public void setUp() {
        List<User> users = userdao.getAllUsers();
        for(User user : users) {
            userdao.deleteUserbyId(user.getId());
        }
    }

    @Test
    void addGetBaseStatTeam() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);

        Team team1 = new Team();
        team1.setName("My First Team");
        team1.setUser(user1);
        team1 = teamdao.addTeam(team1);

        BaseStat basestat1 = new BaseStat();
        String [] temps = basestat1.getBases();
        int i=0;
        for(String temp: temps){
            basestat1.setBaseStat(i,2);
            i++;
        }
        basestat1.setTeam(team1);
        basestat1 = basestatdao.addBaseStatToTeam(basestat1);

        BaseStat fromdao = basestatdao.getBaseStatsOfTeam(team1);

        assertEquals(basestat1,fromdao);
    }

    @Test
    void updateBaseStatOfTeam() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);

        Team team1 = new Team();
        team1.setName("My First Team");
        team1.setUser(user1);
        team1 = teamdao.addTeam(team1);

        BaseStat basestat1 = new BaseStat();
        String [] temps = basestat1.getBases();
        int i=0;
        for(String temp: temps){
            basestat1.setBaseStat(i,2);
            i++;
        }
        basestat1.setTeam(team1);
        basestat1 = basestatdao.addBaseStatToTeam(basestat1);

        BaseStat basestat2 = new BaseStat();
        i = 0;
        for(String temp: temps){
            basestat2.setBaseStat(i,3);
            i++;
        }
        basestat2.setTeam(team1);
        basestat2.setId(basestat1.getId());
        basestatdao.updateBaseStatOfTeam(basestat2);

        BaseStat fromdao = basestatdao.getBaseStatsOfTeam(team1);

        assertEquals(basestat2,fromdao);
        assertNotEquals(basestat1,fromdao);
    }

    @Test
    void deleteBaseStatByTeam() {
        User user1 = new User();
        user1.setName("Douglas Schoedl");
        user1 = userdao.addUser(user1);

        Team team1 = new Team();
        team1.setName("My First Team");
        team1.setUser(user1);
        team1 = teamdao.addTeam(team1);

        BaseStat basestat1 = new BaseStat();
        String [] temps = basestat1.getBases();
        for(String temp: temps){
            int i = 0;
            basestat1.setBaseStat(i,2);
            i++;
        }
        basestat1.setTeam(team1);
        basestat1 = basestatdao.addBaseStatToTeam(basestat1);
        userdao.deleteUserbyId(user1.getId());
        BaseStat fromdao = basestatdao.getBaseStatsOfTeam(team1);
        assertNull(fromdao);
    }
}