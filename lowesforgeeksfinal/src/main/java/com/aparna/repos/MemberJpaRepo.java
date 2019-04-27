package com.aparna.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aparna.entities.Member;

public interface MemberJpaRepo extends JpaRepository<Member, Integer>{
	
	public Member findMemberByFirstNameAndLastName(String firstName, String lastName);
	public Member findMemberByMemberId(Integer memberId);
	
	
	
}
					