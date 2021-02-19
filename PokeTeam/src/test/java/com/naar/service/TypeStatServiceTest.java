package com.naar.service;

import com.naar.data.UserDao;
import com.naar.model.Pokemon;
import com.naar.model.Team;
import com.naar.model.TypeStat;
import com.naar.model.User;
import org.junit.Assert;
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
class TypeStatServiceTest {

    @Autowired
    UserService userservice;

    @Autowired
    UserDao userdao;

    @Autowired
    TeamService teamservice;

    @Autowired
    TypeStatService typestatservice;

    @BeforeEach
    public void setUp() {
        List<User> users = userdao.getAllUsers();
        for(User user : users) {
            userservice.deleteUserById(user.getId());
        }
    }


    @Test
    void getStrengthsFromTypeStat() {
        User user = userservice.createUser("Test");
        Team team = teamservice.createTeamOfUser("team1",user);

        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=0; i<6; i++) {
            Pokemon newpoke = new Pokemon();
            newpoke.setName("test");
            newpoke.setId(1);
            pokemons.add(newpoke);
        }


        TypeStat typeStat = new TypeStat();
        String [] typelist = typeStat.getTypes();
        List<List<String>> poketypes = new ArrayList<>();
        int i = 0;
        int j = 0;
        for(String type: typelist) {
            if(i%3 == 0) {
                poketypes.add(new ArrayList<String>());
                if(i != 0){
                    pokemons.get(j).setType(poketypes.get(j));
                    j++;
                }
            }

            poketypes.get(j).add(type);
            ++i;
        }

        pokemons.get(5).setType(poketypes.get(5));


        typeStat = typestatservice.createTypeStatOfTeam(pokemons,team);
        List<String> strengths = typestatservice.getStrengthsFromTypeStat(typeStat);
        assertEquals(strengths.size(),1);
        assertTrue(strengths.contains("ice"));
    }


    @Test
    void getWeaknessesFromTypeStat() {
        User user = userservice.createUser("Test");
        Team team = teamservice.createTeamOfUser("team1",user);

        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=0; i<6; i++) {
            Pokemon newpoke = new Pokemon();
            newpoke.setName("test");
            newpoke.setId(1);
            pokemons.add(newpoke);
        }


        TypeStat typeStat = new TypeStat();
        String [] typelist = typeStat.getTypes();
        List<List<String>> poketypes = new ArrayList<>();
        int i = 0;
        int j = 0;
        for(String type: typelist) {
            if(i%3 == 0) {
                poketypes.add(new ArrayList<String>());
                if(i != 0){
                    pokemons.get(j).setType(poketypes.get(j));
                    j++;
                }
            }

            poketypes.get(j).add(type);
            ++i;
        }

        pokemons.get(5).setType(poketypes.get(5));


        typeStat = typestatservice.createTypeStatOfTeam(pokemons,team);
        List<String> weaknesses = typestatservice.getWeaknessesFromTypeStat(typeStat);
        assertEquals(weaknesses.size(), 1);
        assertTrue(weaknesses.contains("steel"));
    }




    @Test
    void updateTypeStatOfTeam() {
        User user = userservice.createUser("Test");
        Team team = teamservice.createTeamOfUser("team1",user);

        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=0; i<6; i++) {
            Pokemon newpoke = new Pokemon();
            newpoke.setName("test");
            newpoke.setId(1);
            pokemons.add(newpoke);
        }


        TypeStat typeStat = new TypeStat();
        String [] typelist = typeStat.getTypes();
        List<List<String>> poketypes = new ArrayList<>();
        int i = 0;
        int j = 0;
        for(String type: typelist) {
            if(i%3 == 0) {
                poketypes.add(new ArrayList<String>());
                if(i != 0){
                    pokemons.get(j).setType(poketypes.get(j));
                    j++;
                }
            }

            poketypes.get(j).add(type);
            ++i;
        }

        pokemons.get(5).setType(poketypes.get(5));


        typeStat = typestatservice.createTypeStatOfTeam(pokemons,team);

        List<String> temp = new ArrayList<>();
        temp.add("fire");
        temp.add("water");
        pokemons.get(0).setType(temp);
        boolean updateTest = typestatservice.updateTypeStatOfTeam(pokemons,team);
        assertTrue(updateTest);

        TypeStat fromservice = typestatservice.getTypeStatOfTeam(team);
        assertNotEquals(typeStat,fromservice);

        assertEquals(fromservice.getTypeStat("normal"),-1+6);
        assertEquals(fromservice.getTypeStat("fire"),-3+6);
        assertEquals(fromservice.getTypeStat("water"),-2+6);
        assertEquals(fromservice.getTypeStat("electric"),-2+6);
        assertEquals(fromservice.getTypeStat("grass"),1+6);
        assertEquals(fromservice.getTypeStat("ice"),3+6);
        assertEquals(fromservice.getTypeStat("fighting"),0+6);
        assertEquals(fromservice.getTypeStat("poison"),-3+6);
        assertEquals(fromservice.getTypeStat("ground"),-1+6);
        assertEquals(fromservice.getTypeStat("flying"),-2+6);
        assertEquals(fromservice.getTypeStat("psychic"),1+6);
        assertEquals(fromservice.getTypeStat("bug"),0+6);
        assertEquals(fromservice.getTypeStat("rock"),1+6+1);
        assertEquals(fromservice.getTypeStat("ghost"),-4+6+2);
        assertEquals(fromservice.getTypeStat("dragon"),-1+6);
        assertEquals(fromservice.getTypeStat("dark"),-1+6);
        assertEquals(fromservice.getTypeStat("steel"),-9+6+1);
        assertEquals(fromservice.getTypeStat("fairy"),-3+6);


    }

    @Test
    void createGetTypeStatOfTeam() {
        User user = userservice.createUser("Test");
        Team team = teamservice.createTeamOfUser("team1",user);

        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=0; i<6; i++) {
            Pokemon newpoke = new Pokemon();
            newpoke.setName("test");
            newpoke.setId(1);
            pokemons.add(newpoke);
        }


        TypeStat typeStat = new TypeStat();
        String [] typelist = typeStat.getTypes();
        List<List<String>> poketypes = new ArrayList<>();
        int i = 0;
        int j = 0;
        for(String type: typelist) {
            if(i%3 == 0) {
                poketypes.add(new ArrayList<String>());
                if(i != 0){
                    pokemons.get(j).setType(poketypes.get(j));
                    j++;
                }
            }

            poketypes.get(j).add(type);
            ++i;
        }

        pokemons.get(5).setType(poketypes.get(5));


        typeStat = typestatservice.createTypeStatOfTeam(pokemons,team);
        assertEquals(typeStat.getTypeStat("normal"),-1+6);
        assertEquals(typeStat.getTypeStat("fire"),-3+6);
        assertEquals(typeStat.getTypeStat("water"),-2+6);
        assertEquals(typeStat.getTypeStat("electric"),-2+6);
        assertEquals(typeStat.getTypeStat("grass"),1+6);
        assertEquals(typeStat.getTypeStat("ice"),3+6);
        assertEquals(typeStat.getTypeStat("fighting"),0+6);
        assertEquals(typeStat.getTypeStat("poison"),-3+6);
        assertEquals(typeStat.getTypeStat("ground"),-1+6);
        assertEquals(typeStat.getTypeStat("flying"),-2+6);
        assertEquals(typeStat.getTypeStat("psychic"),1+6);
        assertEquals(typeStat.getTypeStat("bug"),0+6);
        assertEquals(typeStat.getTypeStat("rock"),1+6);
        assertEquals(typeStat.getTypeStat("ghost"),-4+6);
        assertEquals(typeStat.getTypeStat("dragon"),-1+6);
        assertEquals(typeStat.getTypeStat("dark"),-1+6);
        assertEquals(typeStat.getTypeStat("steel"),-9+6);
        assertEquals(typeStat.getTypeStat("fairy"),-3+6);

        TypeStat fromservice = typestatservice.getTypeStatOfTeam(team);
        assertEquals(typeStat,fromservice);


    }




}
