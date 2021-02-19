package com.naar.service;

import java.util.ArrayList;
import java.util.List;

import com.naar.model.Pokemon;
import com.naar.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naar.data.TeamPokemonDaoImpl;
import com.naar.model.TeamPokemon;

@Service
public class TeamPokemonService {
	
	@Autowired
	TeamPokemonDaoImpl teampokemonDaoImpl;
	
	@Autowired
	TeamService teamService;

	//gets team pokemon for a team
	public TeamPokemon getTeamPokemonOfTeam(Team team){
		return teampokemonDaoImpl.getTeamPokemonOfTeam(team);
	}


	//updates the team pokemon of a team (all 6 ids are updated)
	public boolean updateTeamPokemonOfTeam(List<Pokemon> pokes, Team team) {
		TeamPokemon teampoke = teampokemonDaoImpl.getTeamPokemonOfTeam(team);
		List<Integer> pokeids = new ArrayList<>();

		for(Pokemon poke : pokes){
			pokeids.add(poke.getId());
		}

		teampoke.setPokemonId(pokeids);
		return teampokemonDaoImpl.updateTeamPokemonOfTeam(teampoke);
	}

	//creates team pokemon for a team
	public TeamPokemon createTeamPokemonOfTeam(List<Pokemon> pokes, Team team) {
		TeamPokemon teampoke = new TeamPokemon();
		List<Integer> pokeids = new ArrayList<>();

		for(Pokemon poke : pokes){
			pokeids.add(poke.getId());
		}

		teampoke.setTeam(team);
		teampoke.setPokemonId(pokeids);
		teampoke = teampokemonDaoImpl.addTeamPokemonToTeam(teampoke);
		return teampoke;
	}
}
