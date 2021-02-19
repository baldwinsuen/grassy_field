package com.naar.model;

import java.util.HashMap;
import java.util.Map;

//The TypeStats that are for a team. This holds the TypeStats for the entire team it is for.
public class TypeStat {
    private int id;
    private Map<String,Integer> typestats;
    private final String [] types = {"normal",                              //the order of the types
            "fire", "water", "grass", "electric", "ice", "fighting",
            "poison", "ground", "flying", "psychic", "bug", "rock", "ghost",
            "dark", "dragon", "steel", "fairy"};
    private Team team;

    public TypeStat(){
        //initialize each type for type stat for team to be 0
        typestats = new HashMap<>();
        for(String type : types){
            typestats.put(type,6);          //6 was added for determining if team is weak against a stat if negative
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getTypeStat(String type){
        return typestats.get(type);
    }

    public void setTypeStat(String type, Integer stat){
        //this will overwrite the value for the type IT WILL NOT INCREMENT
        typestats.replace(type, typestats.get(type), stat);
    }

    public void incrementTypeStat(String type, Integer stat){
        //this will increment
        typestats.replace(type, typestats.get(type), typestats.get(type) + stat);
    }

    public String [] getTypes(){
        return types;
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

        if (this.id != ((TypeStat)other).id) {
            return false;
        }

        if(!this.typestats.equals(((TypeStat)other).typestats)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

}
