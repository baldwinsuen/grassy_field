package com.naar.model;

import java.util.List;

//This class is used to pass data that our api manipulates back to a front end.
public class Result {
	
	private Team team;
	private List<Pokemon> pokemon;
	private List<Integer> pokeIds;
	private List<String> strength;
	private List<String> weakness;
	private BaseStat basestat;
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public List<Pokemon> getPokemon() {
		return pokemon;
	}
	public List<Integer> getPokeIds() {
		return pokeIds;
	}
	public void setPokeIds(List<Integer> pokeIds) {
		this.pokeIds = pokeIds;
	}
	public void setPokemon(List<Pokemon> pokemon) {
		this.pokemon = pokemon;
	}
	public List<String> getStrength() {
		return strength;
	}
	public void setStrength(List<String> strength) {
		this.strength = strength;
	}
	public List<String> getWeakness() {
		return weakness;
	}
	public void setWeakness(List<String> weakness) {
		this.weakness = weakness;
	}
	public BaseStat getBasestat() {
		return basestat;
	}
	public void setBasestat(BaseStat basestat) {
		this.basestat = basestat;
	}
	
	
}
