package com.naar.data;

import com.naar.model.Team;
import com.naar.model.BaseStat;

public interface BaseStatDao {
    BaseStat getBaseStatsOfTeam(Team team);
    BaseStat addBaseStatToTeam(BaseStat basestat);
    boolean updateBaseStatOfTeam(BaseStat basestat);
    boolean deleteBaseStatByTeam(int teamid);
}
