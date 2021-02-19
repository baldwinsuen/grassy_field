package com.naar.data;

import com.naar.model.Team;
import com.naar.model.TypeStat;


import java.util.Map;

public interface TypeStatDao {

    TypeStat getTypeStatsOfTeam(Team team);
    TypeStat addTypeStatToTeam(TypeStat typestat);
    boolean updateTypeStatOfTeam(TypeStat typestat);
    boolean deleteTypeStatByTeam(int teamid);
}