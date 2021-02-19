package com.naar.service;

import com.naar.data.TypeStatDao;
import com.naar.model.Pokemon;
import com.naar.model.Team;
import com.naar.model.TypeStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TypeStatService {

    @Autowired
    TypeStatDao typestatdao;

    //get the typestats of a team
	public TypeStat getTypeStatOfTeam(Team team){
		return typestatdao.getTypeStatsOfTeam(team);
	}

	//updates type stats by team
	public boolean updateTypeStatOfTeam(List<Pokemon> pokes, Team team){
		TypeStat newtypestat = new TypeStat();
		newtypestat.setTeam(team);

		for(Pokemon poke : pokes){
			List<String> poketypes = poke.getType();
			for(String poketype : poketypes){
				newtypestat = incrementTypeStat(newtypestat,poketype); //incrementTypeStat is important (see below)
			}
		}

		return typestatdao.updateTypeStatOfTeam(newtypestat);
	}



    public TypeStat createTypeStatOfTeam(List<Pokemon> pokes, Team team){
        TypeStat typestat = new TypeStat();


        for(Pokemon poke : pokes){
            List<String> poketypes = poke.getType();
            for(String poketype : poketypes){
            	typestat = incrementTypeStat(typestat,poketype);	//incrementTypeStat is important (see below)
            }
        }

		typestat.setTeam(team);
        typestat = typestatdao.addTypeStatToTeam(typestat);
        return typestat;
    }

    //based on the calculated typestats for a team
	//if a typestat is greater than 7 it is considered a strength and is put into a list of strings
	//denoting a team strength
    public List<String> getStrengthsFromTypeStat(TypeStat typeStat){
		String [] types = typeStat.getTypes();
		List<String> strengths = new ArrayList<>();
		for(String type : types){
			if(typeStat.getTypeStat(type) > 7 ){
				strengths.add(type);
			}
		}
		if(strengths.size() == 0){
			return null;
		}
		return strengths;
	}

	//based on the calculated typestats for a team
	//if a typestat is less than 2 it is considered a weakness and is put into a list of strings
	//denoting a team weakness
	public List<String> getWeaknessesFromTypeStat(TypeStat typeStat){
		String [] types = typeStat.getTypes();
		List<String> weaknesses = new ArrayList<>();
		for(String type : types){
			if(typeStat.getTypeStat(type) < 2 ){
				weaknesses.add(type);
			}
		}
		if(weaknesses.size() == 0){
			return null;
		}
		return weaknesses;
	}

	//this calculates the typestats for a team
	//each typestat for a type starts at 6
	//if a type is twice effective against another type
	//it is raised by one point
	//otherwise if it is half effective it loses 1 point
	//also if its not effective it loses 2 points
    private TypeStat incrementTypeStat(TypeStat typestat, String poketype) {
		int twice = 1;
		int half = -1;
		int not = -2;
		switch (poketype) {
			case "normal":
				typestat.incrementTypeStat("rock", half);
				typestat.incrementTypeStat("ghost", not);
				typestat.incrementTypeStat("steel", half);
				break;
			case "fire":
				typestat.incrementTypeStat("fire", half);
				typestat.incrementTypeStat("water", half);
				typestat.incrementTypeStat("grass", twice);
				typestat.incrementTypeStat("ice", twice);
				typestat.incrementTypeStat("bug", twice);
				typestat.incrementTypeStat("rock", half);
				typestat.incrementTypeStat("dragon", half);
				typestat.incrementTypeStat("steel", twice);
				break;
			case "water":
				typestat.incrementTypeStat("fire", twice);
				typestat.incrementTypeStat("water", half);
				typestat.incrementTypeStat("grass", half);
				typestat.incrementTypeStat("ground", twice);
				typestat.incrementTypeStat("rock", twice);
				typestat.incrementTypeStat("dragon", half);
				break;
			case "electric":
				typestat.incrementTypeStat("water", twice);
				typestat.incrementTypeStat("electric", half);
				typestat.incrementTypeStat("grass", half);
				typestat.incrementTypeStat("ground", not);
				typestat.incrementTypeStat("flying", twice);
				typestat.incrementTypeStat("dragon", half);
				break;
			case "grass":
				typestat.incrementTypeStat("fire", half);
				typestat.incrementTypeStat("water", twice);
				typestat.incrementTypeStat("grass", half);
				typestat.incrementTypeStat("poison", half);
				typestat.incrementTypeStat("ground", twice);
				typestat.incrementTypeStat("flying", half);
				typestat.incrementTypeStat("bug", half);
				typestat.incrementTypeStat("rock", twice);
				typestat.incrementTypeStat("dragon", half);
				typestat.incrementTypeStat("steel", half);
				break;
			case "ice":
				typestat.incrementTypeStat("fire", half);
				typestat.incrementTypeStat("water", half);
				typestat.incrementTypeStat("grass", twice);
				typestat.incrementTypeStat("ice", half);
				typestat.incrementTypeStat("ground", twice);
				typestat.incrementTypeStat("flying", twice);
				typestat.incrementTypeStat("dragon", twice);
				typestat.incrementTypeStat("steel", half);
				break;
			case "fighting":
				typestat.incrementTypeStat("normal", twice);
				typestat.incrementTypeStat("ice", twice);
				typestat.incrementTypeStat("poison", half);
				typestat.incrementTypeStat("flying", half);
				typestat.incrementTypeStat("psychic", half);
				typestat.incrementTypeStat("bug", half);
				typestat.incrementTypeStat("rock", twice);
				typestat.incrementTypeStat("ghost", not);
				typestat.incrementTypeStat("dark", twice);
				typestat.incrementTypeStat("steel", twice);
				typestat.incrementTypeStat("fairy", half);
				break;
			case "poison":
				typestat.incrementTypeStat("grass", twice);
				typestat.incrementTypeStat("poison", half);
				typestat.incrementTypeStat("ground", half);
				typestat.incrementTypeStat("rock", half);
				typestat.incrementTypeStat("ghost", half);
				typestat.incrementTypeStat("steel", not);
				typestat.incrementTypeStat("fairy", twice);
				break;
			case "ground":
				typestat.incrementTypeStat("fire", twice);
				typestat.incrementTypeStat("electric", twice);
				typestat.incrementTypeStat("grass", half);
				typestat.incrementTypeStat("poison", twice);
				typestat.incrementTypeStat("flying", not);
				typestat.incrementTypeStat("bug", half);
				typestat.incrementTypeStat("rock", twice);
				typestat.incrementTypeStat("steel", twice);
				break;
			case "flying":
				typestat.incrementTypeStat("electric", half);
				typestat.incrementTypeStat("grass", twice);
				typestat.incrementTypeStat("fighting", twice);
				typestat.incrementTypeStat("bug", twice);
				typestat.incrementTypeStat("rock", half);
				typestat.incrementTypeStat("steel", half);
				break;
			case "psychic":
				typestat.incrementTypeStat("fighting", twice);
				typestat.incrementTypeStat("poison", twice);
				typestat.incrementTypeStat("psychic", half);
				typestat.incrementTypeStat("dark", not);
				typestat.incrementTypeStat("steel", half);
				break;
			case "bug":
				typestat.incrementTypeStat("fire", half);
				typestat.incrementTypeStat("grass", twice);
				typestat.incrementTypeStat("fighting", half);
				typestat.incrementTypeStat("poison", half);
				typestat.incrementTypeStat("flying", half);
				typestat.incrementTypeStat("psychic", twice);
				typestat.incrementTypeStat("ghost", half);
				typestat.incrementTypeStat("dark", twice);
				typestat.incrementTypeStat("steel", half);
				typestat.incrementTypeStat("fairy", half);
				break;
			case "rock":
				typestat.incrementTypeStat("fire", twice);
				typestat.incrementTypeStat("ice", twice);
				typestat.incrementTypeStat("fighting", half);
				typestat.incrementTypeStat("ground", half);
				typestat.incrementTypeStat("flying", twice);
				typestat.incrementTypeStat("bug", twice);
				typestat.incrementTypeStat("steel", half);
				break;
			case "ghost":
				typestat.incrementTypeStat("normal", not);
				typestat.incrementTypeStat("psychic", twice);
				typestat.incrementTypeStat("ghost", twice);
				typestat.incrementTypeStat("dark", half);
				break;
			case "dragon":
				typestat.incrementTypeStat("dragon", twice);
				typestat.incrementTypeStat("steel", half);
				typestat.incrementTypeStat("fairy", not);
				break;
			case "dark":
				typestat.incrementTypeStat("fighting", half);
				typestat.incrementTypeStat("psychic", twice);
				typestat.incrementTypeStat("ghost", twice);
				typestat.incrementTypeStat("dark", half);
				typestat.incrementTypeStat("fairy", half);
				break;
			case "steel":
				typestat.incrementTypeStat("fire", half);
				typestat.incrementTypeStat("water", half);
				typestat.incrementTypeStat("electric", half);
				typestat.incrementTypeStat("ice", twice);
				typestat.incrementTypeStat("rock", twice);
				typestat.incrementTypeStat("steel", half);
				typestat.incrementTypeStat("fairy", twice);
				break;
			case "fairy":
				typestat.incrementTypeStat("fire", half);
				typestat.incrementTypeStat("fighting", twice);
				typestat.incrementTypeStat("poison", half);
				typestat.incrementTypeStat("dragon", twice);
				typestat.incrementTypeStat("dark", twice);
				typestat.incrementTypeStat("steel", half);
				break;
		}
		return typestat;
	}
}
