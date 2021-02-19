package com.naar.service;

import java.util.List;

import com.naar.data.TeamDao;
import com.naar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naar.model.Team;

@Service
public class TeamService {
	
	@Autowired
	TeamDao teamDao;

	//gets all teams for a user
	public List<Team> getTeamsForUser(int userid){
		return teamDao.getTeamsOfUser(userid);
	}

	//creates a team for a specific user (many teams can be created for a user)
	public Team createTeamOfUser(String name, User user) {
		Team team = new Team();
		team.setName(name);
		team.setUser(user);
		team = teamDao.addTeam(team);
		return team;
	}

	//updates the team(name) by a team id
	public boolean updateTeamById(String name, int teamid){
		Team team = teamDao.getTeamById(teamid);
		team.setName(name);
		return teamDao.updateTeam(team);
	}

	//deletes team and all associated table data (See teamdao.deleteTeamById)
	public boolean deleteTeamById(int teamid){
		return teamDao.deleteTeamById(teamid);
	}

	//gets a team by teamid
	public Team findTeam(int teamid) {
		return teamDao.getTeamById(teamid);
	}
}
