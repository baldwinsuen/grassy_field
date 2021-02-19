package com.naar.service;

import com.naar.data.UserDao;
import com.naar.model.BaseStat;
import com.naar.model.Pokemon;
import com.naar.model.Team;
import com.naar.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BaseStatServiceTest {

    @Autowired
    UserService userservice;

    @Autowired
    UserDao userdao;

    @Autowired
    TeamService teamservice;

    @Autowired
    BaseStatService baseStatService;


    @BeforeEach
    public void setUp() {
        List<User> users = userdao.getAllUsers();
        for(User user : users) {
            userservice.deleteUserById(user.getId());
        }
    }


    @Test
    void updateBaseStatOfTeam() {
        User user = userservice.createUser("Test");
        Team team = teamservice.createTeamOfUser("team1",user);

        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=0; i<6; i++) {
            Pokemon newpoke = new Pokemon();
            newpoke.setName("test");
            newpoke.setId(1);
            List<Integer> pokebases = new ArrayList<>();
            for(int j = i; j<6+i; j++){
                pokebases.add(j+1);
            }
            newpoke.setBasestats(pokebases);
            pokemons.add(newpoke);
        }

        BaseStat basestat = baseStatService.createBaseStatOfTeam(pokemons, team);


        List<Integer> newbases = new ArrayList<>();
        int j=10;
        for(int i=0; i<6 ; i++){
            newbases.add(j++);
        }
        pokemons.get(0).setBasestats(newbases);

        boolean updateTest = baseStatService.updateBaseStatOfTeam(pokemons, team);
        assertTrue(updateTest);

        BaseStat newbasestats = baseStatService.getBaseStatOfTeam(team);
        assertNotEquals(basestat,newbasestats);

        assertEquals(newbasestats.getBaseStat(0),5);
        assertEquals(newbasestats.getBaseStat(1),6);
        assertEquals(newbasestats.getBaseStat(2),7);
        assertEquals(newbasestats.getBaseStat(3),8);
        assertEquals(newbasestats.getBaseStat(4),9);
        assertEquals(newbasestats.getBaseStat(5),10);





    }


    @Test
    void createBaseStatOfTeam() {
        User user = userservice.createUser("Test");
        Team team = teamservice.createTeamOfUser("team1",user);

        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=0; i<6; i++) {
            Pokemon newpoke = new Pokemon();
            newpoke.setName("test");
            newpoke.setId(1);
            List<Integer> pokebases = new ArrayList<>();
            for(int j = i; j<6+i; j++){
                pokebases.add(j+1);
            }
            newpoke.setBasestats(pokebases);
            pokemons.add(newpoke);
        }

        BaseStat basestat = baseStatService.createBaseStatOfTeam(pokemons, team);
        assertEquals(basestat.getBaseStat(0),3);
        assertEquals(basestat.getBaseStat(1),4);
        assertEquals(basestat.getBaseStat(2),5);
        assertEquals(basestat.getBaseStat(3),6);
        assertEquals(basestat.getBaseStat(4),7);
        assertEquals(basestat.getBaseStat(5),8);

        BaseStat fromservice = baseStatService.getBaseStatOfTeam(team);

        assertEquals(basestat,fromservice);


    }
}