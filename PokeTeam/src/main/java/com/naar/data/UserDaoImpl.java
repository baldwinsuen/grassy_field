package com.naar.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.naar.model.Team;
import com.naar.model.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	TeamDaoImpl teamDaoImpl;


	//maps data from trainer table in database to User
    public static final class UserMapper implements RowMapper<User>{
    	
    	@Override
    	public User mapRow(ResultSet rs, int index) throws SQLException{
    		User user = new User();
    		user.setId(rs.getInt("userid"));
            user.setName(rs.getString("trainername"));
            return user;
    	}
    }

    //Gets a list of all Users
	@Override
	public List<User> getAllUsers() {
		final String select_all_users = "select * from trainer";
		return jdbc.query(select_all_users, new UserMapper());
	}

	//Gets a user by an id
	@Override
	public User getUserById(int id) {
		try {
			final String select_user_by_id = "select * from trainer where userid = ?";
			return jdbc.queryForObject(select_user_by_id, new UserMapper(), id);
		}catch(DataAccessException ex) {
			return null;
		}
	}

	//adds a new user to the database
	@Override
	@Transactional
	public User addUser(User user) {
		final String insert_user = "insert into trainer(trainername) values (?)";
		jdbc.update(insert_user, user.getName());
		
		int newid = jdbc.queryForObject("select last_insert_id()", Integer.class);
		user.setId(newid);
		return user;
	}

	//updates a user that exists in the database
	@Override
	public boolean updateUser(User user) {
		final String update_user = "update trainer set trainername = ? where userid = ?";
		return jdbc.update(update_user,
				user.getName(),
				user.getId()) > 0;
	}
	
	
	//deletes a user from the database by id. Any associated teams need to be deleted first to
	//account for the relationship.
	@Override
	@Transactional
	public boolean deleteUserbyId(int id) {
		
		List<Team> teams = teamDaoImpl.getTeamsOfUser(id);
		
		for(Team team: teams) {
			teamDaoImpl.deleteTeamById(team.getId());
		}
		
		final String delete_user_by_id = "DELETE FROM Trainer WHERE userid = ?";
		return jdbc.update(delete_user_by_id, id) > 0;
	}
}
