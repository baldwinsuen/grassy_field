package com.naar.model;


//This is to holds the info that is passed from the front end to the api thought the controller.
public class PokemonPartyInfo {
	
	Pokemon pokemon1;
	Pokemon pokemon2;
	Pokemon pokemon3;
	Pokemon pokemon4;
	Pokemon pokemon5;
	Pokemon pokemon6;

	String trainerName;
	
	String teamName;

	public Pokemon getPokemon1() {
		return pokemon1;
	}

	public void setPokemon1(Pokemon pokemon1) {
		this.pokemon1 = pokemon1;
	}

	public Pokemon getPokemon2() {
		return pokemon2;
	}

	public void setPokemon2(Pokemon pokemon2) {
		this.pokemon2 = pokemon2;
	}

	public Pokemon getPokemon3() {
		return pokemon3;
	}

	public void setPokemon3(Pokemon pokemon3) {
		this.pokemon3 = pokemon3;
	}

	public Pokemon getPokemon4() {
		return pokemon4;
	}

	public void setPokemon4(Pokemon pokemon4) {
		this.pokemon4 = pokemon4;
	}

	public Pokemon getPokemon5() {
		return pokemon5;
	}

	public void setPokemon5(Pokemon pokemon5) {
		this.pokemon5 = pokemon5;
	}

	public Pokemon getPokemon6() {
		return pokemon6;
	}

	public void setPokemon6(Pokemon pokemon6) {
		this.pokemon6 = pokemon6;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
}
