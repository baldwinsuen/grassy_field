package com.naar.data;

import com.naar.model.Team;
import com.naar.model.TypeStat;
import com.naar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.*;
import java.util.List;


@Repository
public class TeamDaoImpl implements TeamDao {


    @Autowired
	JdbcTemplate jdbc;
    @Autowired
    BaseStatDao basestatdao;
    @Autowired
    TypeStatDao typestatdao;
    @Autowired
    TeamPokemonDao teampokemondao;


    //adds team data to database
    @Override
    @Transactional
    public Team addTeam(Team team) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Team (teamname, userid) VALUES (?, ?)";

        jdbc.update((Connection connection) -> {
            PreparedStatement prep = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, team.getName());
            prep.setInt(2, team.getUser().getId());
            return prep;
        }, keyHolder);
        team.setId(keyHolder.getKey().intValue());
        return team;
    }

    //Allows them to change team name
    @Override
    @Transactional
    public boolean updateTeam(Team team) {
        String sql = "UPDATE Team SET teamname = ? WHERE teamid = ?";
        return jdbc.update(sql, team.getName(), team.getId()) > 0;
    }

    //gets all teams
    @Override
    public List<Team> getAllTeams(){
    	final String select_teams_of_user = "select * from team";
		List<Team> teams = jdbc.query(select_teams_of_user, new TeamMapper());
		addUsertoTeams(teams);
		return teams;
    }

    //gets all teams for a specific user
    @Override
    public List<Team> getTeamsOfUser(int userid){
		final String select_teams_of_user = "SELECT * FROM Team WHERE userid = ?";
		List<Team> teams = jdbc.query(select_teams_of_user, new TeamMapper(), userid);
		addUsertoTeams(teams);
		return teams;
	}

	//gets a team by id
    @Override
    public Team getTeamById(int id){
        try {
            String sql = "SELECT * FROM Team WHERE Team.teamid = ?";
            Team team = jdbc.queryForObject(sql, new TeamMapper(), id);
            team.setUser(getUserFromTeam(team));
            return team;
        }catch(DataAccessException ex){
            return null;
        }
    }

    //deletes a team by id
    //needs to delete the teampokemon, typestat, and basestat associated to in in the one to one relationship first
    //for the data deletion to be handled properly
    @Override
    public boolean deleteTeamById(int id){
        teampokemondao.deleteTeamPokemonByTeam(id);
        typestatdao.deleteTypeStatByTeam(id);
        basestatdao.deleteBaseStatByTeam(id);
 	    String sql = "DELETE FROM Team WHERE Team.teamid = " + id;
        return jdbc.update(sql) > 0;
    }

    //helper function to add user to team
    private void addUsertoTeams(List<Team> teams) {
    	for(Team team: teams) {
    		team.setUser(getUserFromTeam(team));
    	}
    }

    //gets a user from a team
    public User getUserFromTeam(Team team){
        String sql = "SELECT * FROM trainer tr INNER JOIN Team T ON tr.userid = T.userid WHERE T.teamid = ?";
        User user = jdbc.queryForObject(sql, new UserDaoImpl.UserMapper(), team.getId());
        System.out.print(user);
        return user;
    }

    //maps data from a team table to team object
    public static final class TeamMapper implements RowMapper<Team> {

        @Override
        public Team mapRow(ResultSet rs, int index) throws SQLException {
            Team team = new Team();
            team.setId(rs.getInt("teamid"));
            team.setName(rs.getString("teamname"));
            return  team;
        }

    }

}
