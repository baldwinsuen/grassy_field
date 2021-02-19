package com.naar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//The BaseStats that are for a team. This holds the BaseStats for the entire team it is for.
public class BaseStat {
    private int id;
    private List<Integer> basestats;
    private final String [] bases = {"hp", "attack",        //this is the order of the stats
         "defense", "spatk", "spdef", "speed"};
    private Team team;

    public BaseStat(){
        //initialize each type for type stat for team to be 0
        basestats = new ArrayList<>();
        for(String base : bases){
            basestats.add(0);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBaseStat(int index){
        return basestats.get(index);
    }

    public List<Integer> getBasestats(){return basestats;}

    public void setBaseStat(int index, Integer stat){
        //this will overwrite the value for the type IT WILL NOT INCREMENT
        basestats.set(index,stat);
    }

    public String [] getBases(){
        return bases;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        if (this.id != ((BaseStat)other).id) {
            return false;
        }

        if(!this.basestats.equals(((BaseStat)other).basestats)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

}
