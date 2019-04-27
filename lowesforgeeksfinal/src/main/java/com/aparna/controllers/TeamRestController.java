package com.aparna.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aparna.entities.Team;
import com.aparna.repos.TeamJpaRepo;

@RestController
@RequestMapping("/lowesforgeeks")
public class TeamRestController {

	
	@Autowired
	TeamJpaRepo teamJpaRepo;

	@RequestMapping(path="/createTeam", method=RequestMethod.POST)
	public ResponseEntity<Void> addTeam(@RequestBody Team team){
		ResponseEntity<Void> re = null;

		Team tea = teamJpaRepo.findTeamByTeamName(team.getTeamName());

		if(tea == null){
			teamJpaRepo.save(tea);
			re = new ResponseEntity<>(HttpStatus.CREATED);
		}
		else{
			re = new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return re;
	}
	
	
	@RequestMapping(path="viewTeam", method=RequestMethod.GET)
	public Team viewExistingTeam(@RequestParam("teamName")String teamName)
	{
		return teamJpaRepo.findTeamByTeamName(teamName);
	}
	
	
	
	@Transactional
	@RequestMapping(path="/updateTeam", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateTeam(@RequestBody Team team )
	{	
		Team tem = teamJpaRepo.findTeamByTeamId(team.getTeamId());
		if(tem==null)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		tem.setTeamName(team.getTeamName());
		tem.setMembers(team.getMembers());
		
		teamJpaRepo.save(tem);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
}
