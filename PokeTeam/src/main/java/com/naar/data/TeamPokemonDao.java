package com.naar.data;

import java.util.List;

import com.naar.model.BaseStat;
import com.naar.model.Team;
import com.naar.model.TeamPokemon;

public interface TeamPokemonDao {

	TeamPokemon getTeamPokemonOfTeam(Team team);
	TeamPokemon addTeamPokemonToTeam(TeamPokemon teampokemon);
	boolean updateTeamPokemonOfTeam(TeamPokemon teampokemon);
	boolean deleteTeamPokemonByTeam(int teamid);
}
