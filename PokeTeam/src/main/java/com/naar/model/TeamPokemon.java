package com.naar.model;

import java.util.ArrayList;
import java.util.List;

//Pokemon that are in a team. This just holds the id of the pokemon and the team it is for.
public class TeamPokemon {
	
	private int id;
	private List<Integer> pokemonId = new ArrayList<>();
	private Team team;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getPokemonId() {
		return pokemonId;
	}
	public void setPokemonId(List<Integer> pokemonId) {
		this.pokemonId = pokemonId;
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

		if (this.id != ((TeamPokemon)other).id) {
			return false;
		}

		if(!this.pokemonId.equals(((TeamPokemon)other).pokemonId)){
			return false;
		}
		
		if(!this.team.equals(((TeamPokemon)other).team)) {
			return false;
		}

		return true;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
}
