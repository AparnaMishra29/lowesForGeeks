package com.aparna.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aparna.entities.Member;
import com.aparna.repos.MemberJpaRepo;

@RestController
@RequestMapping("/lowesforgeeks")
public class MemberRestController {

	@Autowired
	MemberJpaRepo memberJpaRepo;

	@RequestMapping(path="/createMember", method=RequestMethod.POST)
	public ResponseEntity<Void> addMember(@RequestBody Member member){
		ResponseEntity<Void> re = null;

		Member meme = memberJpaRepo.findMemberByFirstNameAndLastName(member.getFirstName(),member.getLastName());
		System.out.println(member);

		if(meme == null){
			memberJpaRepo.save(member);
			re = new ResponseEntity<>(HttpStatus.CREATED);
		}
		else{
			re = new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return re;
	}


	@RequestMapping(path="viewMember", method=RequestMethod.GET)
	public Member viewExistingMember(@RequestParam("firstName")String firstName, @RequestParam("lastName")String lastName)
	{
		return memberJpaRepo.findMemberByFirstNameAndLastName(firstName, lastName); 
	}

	@Transactional
	@RequestMapping(path="/updateMember", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateMember(@RequestBody Member member )
	{	
		Member mem = memberJpaRepo.findMemberByMemberId(member.getMemberId());
		if(mem==null)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		mem.setFirstName(member.getFirstName());
		mem.setLastName(member.getLastName());
		mem.setEmail(member.getEmail());
		memberJpaRepo.save(mem);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
}
