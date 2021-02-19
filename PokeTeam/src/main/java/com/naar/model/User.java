package com.naar.model;

//User or Trainer that can have many teams
public class User {

	private int id;
	private String name;
	//private String password;

	//Getters=============================================
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	//Setters=============================================
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public boolean equals(Object other)
	{
		if (other == null) {
			return false;
		}

		if (this.getClass() != other.getClass()) {
			return false;
		}

		if (this.id != ((User)other).id) {
			return false;
		}

		if(!this.name.equals(((User)other).name)){
			return false;
		}

		return true;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
}
