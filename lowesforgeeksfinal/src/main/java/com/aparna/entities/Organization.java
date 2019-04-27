package com.aparna.entities;

import java.util.List;

public class Organization {

	List<Member> members;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Organization [members=");
		builder.append(members);
		builder.append("]");
		return builder.toString();
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Organization(List<Member> members) {
		super();
		this.members = members;
	}
	
	public Organization()
	{
		
	}
}
