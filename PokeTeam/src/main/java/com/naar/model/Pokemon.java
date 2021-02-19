package com.naar.model;

import java.util.*;

public class Pokemon {
	
	private int id;
	private String name;
	private List<String> type = new ArrayList<>();
	private List<Integer> basestats = new ArrayList<>(6);
    
    public Pokemon() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int pokemonId) {
		this.id = pokemonId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}


	public List<Integer> getBasestats() {
		return basestats;
	}

	public void setBasestats(List<Integer> basestats) {
		this.basestats = basestats;
	}

	@Override
    public int hashCode() {
        return this.id;
    }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
    
    
}
