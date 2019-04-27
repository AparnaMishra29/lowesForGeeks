package com.aparna.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aparna.entities.Team;

public interface TeamJpaRepo extends JpaRepository<Team, Integer>{
	

	public Team findTeamByTeamName(String teamName);

	public Team findTeamByTeamId(Integer teamId);

}
