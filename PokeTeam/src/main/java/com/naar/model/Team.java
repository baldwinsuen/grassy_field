package com.naar.model;

//A pokemon team note that if team is one to one with BaseStats, TypeStats and TeamPokemon
//As such if a team object is instantiated a BaseStats, TypeStats and Team pokemon need to be created as well
public class Team {

	private int id;
	private String name;
	private User user;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}

		if (this.getClass() != other.getClass()) {
			return false;
		}

		if (this.id != ((Team)other).id) {
			return false;
		}

		if(!this.name.equals(((Team)other).name)){
			return false;
		}
		
		if(!this.user.equals(((Team)other).user)) {
			return false;
		}

		return true;
	}
}
