package com.aparna.entities;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Team 
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer teamId;
	
	String teamName;
	List<Member> members;
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Team [teamId=");
		builder.append(teamId);
		builder.append(", teamName=");
		builder.append(teamName);
		builder.append(", members=");
		builder.append(members);
		builder.append("]");
		return builder.toString();
	}
	public Team(Integer teamId, String teamName, List<Member> members) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.members = members;
	}
	
	
}
