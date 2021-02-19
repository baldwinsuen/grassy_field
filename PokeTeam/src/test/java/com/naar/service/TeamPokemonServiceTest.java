package com.naar.service;

import com.naar.data.UserDao;
import com.naar.model.Pokemon;
import com.naar.model.Team;
import com.naar.model.TeamPokemon;
import com.naar.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TeamPokemonServiceTest {

    @Autowired
    UserService userservice;

    @Autowired
    UserDao userdao;

    @Autowired
    TeamService teamservice;

    @Autowired
    TeamPokemonService teampokemonservice;

    @BeforeEach
    public void setUp() {
        List<User> users = userdao.getAllUsers();
        for(User user : users) {
            userservice.deleteUserById(user.getId());
        }
    }

    @Test
    void updateTeamPokemonOfTeam() {
        User user = userservice.createUser("Test");
        Team team = teamservice.createTeamOfUser("team1",user);

        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=0; i<6; i++) {
            Pokemon newpoke = new Pokemon();
            newpoke.setName("test");
            newpoke.setId(i+1);
            pokemons.add(newpoke);
        }

        TeamPokemon teamPokemon = teampokemonservice.createTeamPokemonOfTeam(pokemons, team);
        pokemons.get(2).setId(151);

        boolean updateTest = teampokemonservice.updateTeamPokemonOfTeam(pokemons,team);
        assertTrue(updateTest);

        TeamPokemon fromservice = teampokemonservice.getTeamPokemonOfTeam(team);

        assertNotEquals(teamPokemon,fromservice);
        List<Integer> pokeids = fromservice.getPokemonId();
        assertEquals(pokeids.size(),6);
        assertTrue(pokeids.contains(1));
        assertTrue(pokeids.contains(2));
        assertTrue(pokeids.contains(151));
        assertTrue(pokeids.contains(4));
        assertTrue(pokeids.contains(5));
        assertTrue(pokeids.contains(6));


    }

    @Test
    void createGetTeamPokemonOfTeam() {
        User user = userservice.createUser("Test");
        Team team = teamservice.createTeamOfUser("team1",user);

        List<Pokemon> pokemons = new ArrayList<>();
        for(int i=0; i<6; i++) {
            Pokemon newpoke = new Pokemon();
            newpoke.setName("test");
            newpoke.setId(i+1);
            pokemons.add(newpoke);
        }

        TeamPokemon teamPokemon = teampokemonservice.createTeamPokemonOfTeam(pokemons, team);
        List<Integer> pokeids = teamPokemon.getPokemonId();
        assertEquals(pokeids.size(),6);
        assertTrue(pokeids.contains(1));
        assertTrue(pokeids.contains(2));
        assertTrue(pokeids.contains(3));
        assertTrue(pokeids.contains(4));
        assertTrue(pokeids.contains(5));
        assertTrue(pokeids.contains(6));

        TeamPokemon fromservice = teampokemonservice.getTeamPokemonOfTeam(team);
        assertEquals(teamPokemon, fromservice);


    }
}