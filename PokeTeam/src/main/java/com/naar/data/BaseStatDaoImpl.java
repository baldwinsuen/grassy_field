package com.naar.data;

import com.naar.model.BaseStat;
import com.naar.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

@Repository
public class BaseStatDaoImpl implements BaseStatDao {

    @Autowired
    JdbcTemplate jdbc;

    //maps data from a basestat table to a basestat object
    private static final class BaseStatMapper implements RowMapper<BaseStat> {

        @Override
        public BaseStat mapRow(ResultSet rs, int index) throws SQLException {
            BaseStat basestat = new BaseStat();

            basestat.setId(rs.getInt("basestatid"));
            String [] cols = basestat.getBases();
            int i=0;
            for(String col : cols) {
                basestat.setBaseStat(i++, rs.getInt(col));
            }

            return basestat;
        }

    }

    //gets the basestats for a specific team
    @Override
    public BaseStat getBaseStatsOfTeam(Team team){
        try {
            String sql = "SELECT * FROM BaseStat WHERE teamid = ?";
            BaseStat basestat = jdbc.queryForObject(sql, new BaseStatDaoImpl.BaseStatMapper(), team.getId());
            basestat.setTeam(team);
            return basestat;
        }catch(DataAccessException ex) {
            return null;
        }
    }

    //team must be in basestat to use this
    @Override
    @Transactional
    public BaseStat addBaseStatToTeam(BaseStat basestat){
        //the order matters here it matches the order of final string [] types in TypeStat
        final String sql = "INSERT INTO BaseStat (hp, attack, defense, spatk, spdef, "
                + "speed, teamid) VALUES (?,?,?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection connection) -> {
            PreparedStatement prep = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            String [] cols = basestat.getBases(); //gets bases in the order shown above in sql statement
            int i=1;
            for(String col : cols){
                prep.setInt(i, basestat.getBaseStat(i-1));
                i++;
            }
            prep.setInt(7, basestat.getTeam().getId());
            return prep;
        }, keyHolder);
        basestat.setId(keyHolder.getKey().intValue());
        return basestat;
    }

    //team must be in basestat to use this
    //updates the basestats for a specific team
    @Override
    @Transactional
    public boolean updateBaseStatOfTeam(BaseStat basestat){
        //the order matters here it matches the order of final string [] types in TypeStat
        final String sql = "UPDATE BaseStat SET hp = ?, attack = ?, defense= ?, "
                + "spatk = ?, spdef = ?, speed = ? WHERE teamid = ?";
        return jdbc.update((Connection connection) -> {
            PreparedStatement prep = connection.prepareStatement(sql);
            String [] cols = basestat.getBases(); //gets bases in the order shown above in sql statement
            int i=1;
            for(String col : cols){
                prep.setInt(i, basestat.getBaseStat(i-1));
                i++;
            }
            prep.setInt(7, basestat.getTeam().getId());
            return prep;
        }) > 0;
    }

    //deletes the basestats for a specific team
    @Override
    public boolean deleteBaseStatByTeam(int teamid){
        final String sql = "DELETE FROM BaseStat WHERE teamid = ?";
        return jdbc.update(sql, teamid) > 0;
    }

}
