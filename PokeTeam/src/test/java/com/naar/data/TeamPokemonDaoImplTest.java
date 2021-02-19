package com.naar.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.naar.model.BaseStat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.naar.model.Team;
import com.naar.model.TeamPokemon;
import com.naar.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class TeamPokemonDaoImplTest {

	@Autowired
	TeamPokemonDao teampokeDao;
	
	@Autowired
	TeamDao teamDao;
	
	@Autowired
	UserDao userDao;
	
	@BeforeEach
	public void setUp() {
		List<User> users = userDao.getAllUsers();
		for(User user: users) {
			System.out.println("TEST");
			userDao.deleteUserbyId(user.getId());
		}
	}
	
	@Test
	public void testAddTeamPokemon() {
		User user1 = new User();
		user1.setName("Collin");
		user1 = userDao.addUser(user1);
		
		Team team1 = new Team();
		team1.setName("first team");
		team1.setUser(user1);
		team1 = teamDao.addTeam(team1);
		
		TeamPokemon teampoke1 = new TeamPokemon();
		List<Integer> temp = new ArrayList<>();
		temp.add(1);
		temp.add(2);
		temp.add(3);
		temp.add(4);
		temp.add(5);
		temp.add(6);
		teampoke1.setPokemonId(temp);
		teampoke1.setTeam(team1);
		teampoke1 = teampokeDao.addTeamPokemonToTeam(teampoke1);
		
		TeamPokemon fromDao = teampokeDao.getTeamPokemonOfTeam(team1);
		
		assertEquals(fromDao, teampoke1);
	}
	
	@Test
	public void testUpdateTeamPokemon() {
		User user1 = new User();
		user1.setName("Collin");
		user1 = userDao.addUser(user1);
		
		Team team1 = new Team();
		team1.setName("first team");
		team1.setUser(user1);
		team1 = teamDao.addTeam(team1);
		
		TeamPokemon teampoke1 = new TeamPokemon();
		List<Integer> temp = new ArrayList<>();
		temp.add(1);
		temp.add(2);
		temp.add(3);
		temp.add(4);
		temp.add(5);
		temp.add(6);
		teampoke1.setPokemonId(temp);
		teampoke1.setTeam(team1);
		teampoke1 = teampokeDao.addTeamPokemonToTeam(teampoke1);
		
		TeamPokemon fromDao = teampokeDao.getTeamPokemonOfTeam(team1);
		temp.set(0, 151);
		teampoke1.setPokemonId(temp);
		teampokeDao.updateTeamPokemonOfTeam(teampoke1);


		assertNotEquals(fromDao, teampoke1);
		
		fromDao = teampokeDao.getTeamPokemonOfTeam(team1);
		
		assertEquals(fromDao, teampoke1);
	}
	
	@Test
	public void testDeletebyTeamId() {
		User user1 = new User();
		user1.setName("Collin");
		user1 = userDao.addUser(user1);
		
		Team team1 = new Team();
		team1.setName("first team");
		team1.setUser(user1);
		team1 = teamDao.addTeam(team1);
		
		TeamPokemon teampoke1 = new TeamPokemon();
		List<Integer> temp = new ArrayList<>();
		temp.add(1);
		temp.add(2);
		temp.add(3);
		temp.add(4);
		temp.add(5);
		temp.add(6);
		teampoke1.setPokemonId(temp);
		teampoke1.setTeam(team1);
		teampoke1 = teampokeDao.addTeamPokemonToTeam(teampoke1);
		userDao.deleteUserbyId(user1.getId());
		TeamPokemon fromdao = teampokeDao.getTeamPokemonOfTeam(team1);
		assertNull(fromdao);


	}
}
