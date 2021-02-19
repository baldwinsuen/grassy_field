package com.naar.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.naar.model.Team;
import com.naar.model.TeamPokemon;


@Repository
public class TeamPokemonDaoImpl implements TeamPokemonDao{
	
	@Autowired
	JdbcTemplate jdbc;

	//maps PokemonInTeam table in sql database to a teampokemon object
	private static final class TeamPokemonMapper implements RowMapper<TeamPokemon>{

		@Override
		public TeamPokemon mapRow(ResultSet rs, int index) throws SQLException{
			TeamPokemon teampoke = new TeamPokemon();

			teampoke.setId(rs.getInt("pokemonteamid"));
			List<Integer> temp = new ArrayList<>();
			String[] colnames = {"pokemon1", "pokemon2", "pokemon3", "pokemon4", "pokemon5", "pokemon6"};
			for(int i = 0; i < colnames.length; i++) {
				temp.add(rs.getInt(colnames[i]));
			}
			teampoke.setPokemonId(temp);
			return teampoke;
		}
	}


	//gets teampokemon from a team
	@Override
	public TeamPokemon getTeamPokemonOfTeam(Team team) {
		try {
			String sql = "SELECT * FROM PokemonInTeam WHERE teamid = ?" ;
			TeamPokemon teampokemon = jdbc.queryForObject(sql, new TeamPokemonDaoImpl.TeamPokemonMapper(), team.getId());
			teampokemon.setTeam(team);
			return teampokemon;
		}catch(DataAccessException ex) {
			return null;
		}
	}


	//adds Teampokemon to the database for a specific team
	@Override
	@Transactional
	public TeamPokemon addTeamPokemonToTeam(TeamPokemon teampoke) {
		final String sql = "INSERT INTO PokemonInTeam (pokemon1, pokemon2, pokemon3, pokemon4, "
		+ "pokemon5, pokemon6, teamid) VALUES (?,?,?,?,?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update((Connection connection) -> {
			PreparedStatement prep = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			List<Integer> pokemonids = teampoke.getPokemonId(); //gets pokemon ids (order does not matter)
			for(int i=0; i<pokemonids.size(); i++){
				prep.setInt(i+1, pokemonids.get(i));
			}
			prep.setInt(7, teampoke.getTeam().getId());
			return prep;
		}, keyHolder);
		teampoke.setId(keyHolder.getKey().intValue());
		return teampoke;

	}

	//updates teampokemon in database for a specific team
	@Override
	public boolean updateTeamPokemonOfTeam(TeamPokemon teampoke) {
		final String sql = "UPDATE PokemonInTeam SET pokemon1 = ?, pokemon2 = ?, pokemon3 = ?, pokemon4 = ?, pokemon5 = ?, "
				+ "pokemon6 = ? WHERE teamid = ?";

		return jdbc.update((Connection connection) -> {
			PreparedStatement prep = connection.prepareStatement(sql);
			List<Integer> pokemonids = teampoke.getPokemonId();	//gets ids to updates all pokemon ids in teampokemon database
			for(int i=0; i<pokemonids.size(); i++){				//for a team
				prep.setInt(i+1, pokemonids.get(i));
			}
			prep.setInt(7, teampoke.getTeam().getId());
			return prep;
		}) > 0;
	}

	//delets teampokemon for a specific team
	@Override
	@Transactional
	public boolean deleteTeamPokemonByTeam(int teamid) {
		final String sql = "DELETE FROM PokemonInTeam WHERE teamid = ?";
		return jdbc.update(sql, teamid) > 0;
		
	}
}
