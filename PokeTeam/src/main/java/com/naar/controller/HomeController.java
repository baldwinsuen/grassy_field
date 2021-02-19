package com.naar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.naar.model.*;
import com.naar.service.*;
import org.springframework.web.bind.annotation.CrossOrigin;

// @CrossOrigin(origins = "https://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/grassyfield")
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	TeamPokemonService teampokemonService;
	
	@Autowired
	TypeStatService typestatService;
	
	@Autowired
	BaseStatService basestatService;

	//endpoint for creating a user team and associated stats, pokemon in team all at once
	//see result class for what will be passed back
	@PostMapping("/create")
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Result create(@RequestBody PokemonPartyInfo pokepartyinfo) {
		User user = userService.createUser(pokepartyinfo.getTrainerName());
		Team team = teamService.createTeamOfUser(pokepartyinfo.getTeamName(), user);
		
		List<Pokemon> pokes = new ArrayList<>();
		pokes.add(pokepartyinfo.getPokemon1());
		pokes.add(pokepartyinfo.getPokemon2());
		pokes.add(pokepartyinfo.getPokemon3());
		pokes.add(pokepartyinfo.getPokemon4());
		pokes.add(pokepartyinfo.getPokemon5());
		pokes.add(pokepartyinfo.getPokemon6());
		
		TeamPokemon teampoke = teampokemonService.createTeamPokemonOfTeam(pokes, team);
		
		TypeStat typestat = typestatService.createTypeStatOfTeam(pokes, team);
		List<String> strengths = typestatService.getStrengthsFromTypeStat(typestat);
		List<String> weaknesses = typestatService.getWeaknessesFromTypeStat(typestat);
		
		BaseStat basestat = basestatService.createBaseStatOfTeam(pokes, team);
		
		Result createdResult = new Result();
		createdResult.setTeam(team);
		createdResult.setPokemon(pokes);
		createdResult.setPokeIds(teampoke.getPokemonId());
		createdResult.setStrength(strengths);
		createdResult.setWeakness(weaknesses);
		createdResult.setBasestat(basestat);
		
		return createdResult;
	}

	//endpoint for creating a user with a name
	//it returns a user
	@PostMapping("/create_user/{name}")
	@CrossOrigin
	@ResponseBody
	public User createUser(@PathVariable String name) {
		return userService.createUser(name);
	}

	//endpoint for creating a team for a user see pokepartyinfo object for what is passed in and
	//result for what is passed back
	@PostMapping("/create_team/{userid}")
	@CrossOrigin
	@ResponseBody
	public Result createTeam(@RequestBody PokemonPartyInfo pokepartyinfo, @PathVariable int userid) {
		User user = userService.findUser(userid);
		
		Team team = teamService.createTeamOfUser(pokepartyinfo.getTeamName(), user);
		
		List<Pokemon> pokes = new ArrayList<>();
		pokes.add(pokepartyinfo.getPokemon1());
		pokes.add(pokepartyinfo.getPokemon2());
		pokes.add(pokepartyinfo.getPokemon3());
		pokes.add(pokepartyinfo.getPokemon4());
		pokes.add(pokepartyinfo.getPokemon5());
		pokes.add(pokepartyinfo.getPokemon6());
		
		TeamPokemon teampoke = teampokemonService.createTeamPokemonOfTeam(pokes, team);
		
		TypeStat typestat = typestatService.createTypeStatOfTeam(pokes, team);
		List<String> strengths = typestatService.getStrengthsFromTypeStat(typestat);
		List<String> weaknesses = typestatService.getWeaknessesFromTypeStat(typestat);
		
		BaseStat basestat = basestatService.createBaseStatOfTeam(pokes, team);
		
		Result createdResult = new Result();
		createdResult.setTeam(team);
		createdResult.setPokemon(pokes);
		createdResult.setPokeIds(teampoke.getPokemonId());
		createdResult.setStrength(strengths);
		createdResult.setWeakness(weaknesses);
		createdResult.setBasestat(basestat);
		
		return createdResult;
	}
	
	//returns a result containing team, basestat, typestat, and teampokemon info
	@GetMapping("/team/{teamid}")
	@CrossOrigin
	public Result PokemonofTeam(@PathVariable int teamid){
		Team team = teamService.findTeam(teamid);
		TeamPokemon teampoke = teampokemonService.getTeamPokemonOfTeam(team);
		Result result = new Result();
		result.setPokeIds(teampoke.getPokemonId());
		
		BaseStat basestat = basestatService.getBaseStatOfTeam(team);
		TypeStat typestat = typestatService.getTypeStatOfTeam(team);
		List<String> strength = typestatService.getStrengthsFromTypeStat(typestat);
		List<String> weakness = typestatService.getWeaknessesFromTypeStat(typestat);
		
		result.setBasestat(basestat);
		result.setStrength(strength);
		result.setWeakness(weakness);
		return result;
	}

	//returns a list of all results containing team, basestat, typestat, and teampokemon info for a user
	@GetMapping("/teams/{userid}")
	@CrossOrigin
	public List<Result> TeamsofUser(@PathVariable int userid){
		List<Result> results = new ArrayList<>();
		
		List<Team> teams = teamService.getTeamsForUser(userid);
		
		for(Team team: teams) {
			Result result = new Result();
			result.setTeam(team);
			TeamPokemon teampoke = teampokemonService.getTeamPokemonOfTeam(team);
			result.setPokeIds(teampoke.getPokemonId());
			
			BaseStat basestat = basestatService.getBaseStatOfTeam(team);
			TypeStat typestat = typestatService.getTypeStatOfTeam(team);
			List<String> strength = typestatService.getStrengthsFromTypeStat(typestat);
			List<String> weakness = typestatService.getWeaknessesFromTypeStat(typestat);
			
			result.setBasestat(basestat);
			result.setStrength(strength);
			result.setWeakness(weakness);
			results.add(result);
		}
		return results;
	}

	//returns info for a user
	@GetMapping("/user/{name}")
	@CrossOrigin
	public User getUser(@PathVariable String name) {
		return userService.findUserByName(name);

	}
	
	//returns the type strengths and weakness of a team
	@GetMapping("/strength_weakness/{teamid}")
	@CrossOrigin
	public Result GetStrengthWeaknessofTeam(@PathVariable int teamid){
		Team team = teamService.findTeam(teamid);
		TypeStat typestat = typestatService.getTypeStatOfTeam(team);
		List<String> strengths = typestatService.getStrengthsFromTypeStat(typestat);
		List<String> weaknesses = typestatService.getWeaknessesFromTypeStat(typestat);
		
		Result result = new Result();
		result.setStrength(strengths);
		result.setWeakness(weaknesses);
		
		return result;
	}

	//updates a basestats, typestats, and teampokemon for an already created team
	@PutMapping("/update_pokemon/{teamid}")
	@CrossOrigin
	@ResponseBody
	public Result updatePokemon(@RequestBody PokemonPartyInfo pokepartyinfo, @PathVariable int teamid) {
		
		Team team = teamService.findTeam(teamid);
		
		List<Pokemon> pokes = new ArrayList<>();
		pokes.add(pokepartyinfo.getPokemon1());
		pokes.add(pokepartyinfo.getPokemon2());
		pokes.add(pokepartyinfo.getPokemon3());
		pokes.add(pokepartyinfo.getPokemon4());
		pokes.add(pokepartyinfo.getPokemon5());
		pokes.add(pokepartyinfo.getPokemon6());
		
		teampokemonService.updateTeamPokemonOfTeam(pokes, team);
		typestatService.updateTypeStatOfTeam(pokes, team);
		basestatService.updateBaseStatOfTeam(pokes, team);
		
		TeamPokemon teampoke = teampokemonService.getTeamPokemonOfTeam(team);
		BaseStat basestat = basestatService.getBaseStatOfTeam(team);
		TypeStat typestat = typestatService.getTypeStatOfTeam(team);
		List<String> strengths = typestatService.getStrengthsFromTypeStat(typestat);
		List<String> weaknesses = typestatService.getWeaknessesFromTypeStat(typestat);
		
		Result updatedResult = new Result();
		updatedResult.setTeam(team);
		updatedResult.setPokemon(pokes);
		updatedResult.setPokeIds(teampoke.getPokemonId());
		updatedResult.setStrength(strengths);
		updatedResult.setWeakness(weaknesses);
		updatedResult.setBasestat(basestat);
		
		return updatedResult;
	}
}
