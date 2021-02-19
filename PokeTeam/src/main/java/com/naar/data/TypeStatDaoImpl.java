package com.naar.data;


import com.naar.model.Team;
import com.naar.model.TypeStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;


@Repository
public class TypeStatDaoImpl implements TypeStatDao{

    @Autowired
    JdbcTemplate jdbc;

    //Maps typestat table in database to a typestat object
    private static final class TypeStatMapper implements RowMapper<TypeStat> {

        @Override
        public TypeStat mapRow(ResultSet rs, int index) throws SQLException {
            TypeStat typestat = new TypeStat();

            typestat.setId(rs.getInt("typestatid"));
            String [] cols = typestat.getTypes();

            for(String col : cols) {
                typestat.setTypeStat(col, rs.getInt(col));
            }
            return typestat;
        }

    }

    //gets typestats of a team
    @Override
    public TypeStat getTypeStatsOfTeam(Team team){
        try {
            String sql = "SELECT * FROM TypeStat WHERE teamid = ?";
            TypeStat typestat = jdbc.queryForObject(sql, new TypeStatDaoImpl.TypeStatMapper(), team.getId());
            typestat.setTeam(team);
            return typestat;
        }catch(DataAccessException ex) {
            return null;
        }
    }


    //team must be in typestat to use this
    //adds typestats to a team
    @Override
    @Transactional
    public TypeStat addTypeStatToTeam(TypeStat typestat){
        //the order matters here it matches the order of final string [] types in TypeStat
        final String sql = "INSERT INTO TypeStat (normal, fire, water, grass, electric, "
         + "ice, fighting, poison, ground, flying, psychic, bug, rock, ghost, "
         + "dark, dragon, steel, fairy, teamid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection connection) -> {
            PreparedStatement prep = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            String [] cols = typestat.getTypes();   //gets all types in order shown above
            int i=1;
            for(String col : cols){ //iterates through type stats in order
                prep.setInt(i++, typestat.getTypeStat(col));
            }
            prep.setInt(19, typestat.getTeam().getId());    //adds teamid
            return prep;
        }, keyHolder);
        typestat.setId(keyHolder.getKey().intValue());
        return typestat;
    }

    //team must be in typestat to use this
    @Override
    @Transactional
    public boolean updateTypeStatOfTeam(TypeStat typestat){
        //the order matters here it matches the order of final string [] types in TypeStat
        final String sql = "UPDATE TypeStat SET normal = ?, fire = ?, water= ?, "
                + "grass = ?, electric = ?, ice = ?, fighting = ?, poison = ?, ground = ?, "
                + "flying = ?, psychic = ?, bug = ?, rock = ?, ghost = ?, dark = ?, "
                + "dragon = ?, steel = ?, fairy = ? WHERE teamid = ?";
        return jdbc.update((Connection connection) -> {
            PreparedStatement prep = connection.prepareStatement(sql);
            String [] cols = typestat.getTypes(); //gets all types in order shown above
            int i=1;
            for(String col : cols){ //iterates through type stats in order
                prep.setInt(i++, typestat.getTypeStat(col));
            }
            prep.setInt(19, typestat.getTeam().getId());
            return prep;
        }) > 0;
    }

    //deletes typestats with an associated teamid
    @Override
    @Transactional
    public boolean deleteTypeStatByTeam(int teamid){
        final String sql = "DELETE FROM TypeStat WHERE teamid = ?";
        return jdbc.update(sql, teamid) > 0;
    }

}
