package com.naar.service;

import com.naar.data.BaseStatDao;
import com.naar.model.BaseStat;
import com.naar.model.Pokemon;
import com.naar.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseStatService {

    @Autowired
    BaseStatDao basestatdao;

    //gets the basestats of a team
    public BaseStat getBaseStatOfTeam(Team team){
        return basestatdao.getBaseStatsOfTeam(team);
    }

    //add 6 pokemon stats up then divides by 6 for an average for the team
    public boolean updateBaseStatOfTeam(List<Pokemon> pokes, Team team) {
        BaseStat basestat = basestatdao.getBaseStatsOfTeam(team);
        Integer [] stats = {0,0,0,0,0,0}; //hp atk def spatk spdef speed

        String [] bases = basestat.getBases();

        for(Pokemon poke : pokes){
            int i = 0;
            List<Integer> pokebasestats = poke.getBasestats();
            for(String base : bases){
                stats[i] += pokebasestats.get(i);
                i++;
            }
        }

        int i = 0;
        for(String base : bases) {
            basestat.setBaseStat(i, Math.floorDiv(stats[i], 6));
            i++;
        }
        return basestatdao.updateBaseStatOfTeam(basestat);
    }

    //add 6 pokemon stats up then divides by 6 for an average for the team
    public BaseStat createBaseStatOfTeam(List<Pokemon> pokes, Team team){
        BaseStat basestat = new BaseStat();
        Integer [] stats = {0,0,0,0,0,0}; //hp atk def spatk spdef speed
        //add 6 pokemon stats then divide by 6 for average
        String [] bases = basestat.getBases();


        for(Pokemon poke : pokes){
            int i = 0;
            List<Integer> pokebasestats = poke.getBasestats();
            for(String base : bases){
                stats[i] += pokebasestats.get(i);
                i++;
            }
        }

        int i = 0;
        for(String base : bases) {
            basestat.setBaseStat(i, Math.floorDiv(stats[i], 6));
            i++;
        }
        basestat.setTeam(team);
        basestat = basestatdao.addBaseStatToTeam(basestat);
        return basestat;
    }


}
