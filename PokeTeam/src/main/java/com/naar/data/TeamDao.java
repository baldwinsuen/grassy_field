package com.naar.data;

import java.util.List;

import com.naar.model.Team;

public interface TeamDao {
	
	List<Team> getAllTeams();
	List<Team> getTeamsOfUser(int userid);
	Team getTeamById(int id);
	Team addTeam(Team team);
	boolean updateTeam(Team team);
	boolean deleteTeamById(int id);

}
