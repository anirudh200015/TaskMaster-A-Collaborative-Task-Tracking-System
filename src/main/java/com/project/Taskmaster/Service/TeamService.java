package com.project.Taskmaster.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Taskmaster.Repository.TeamRepository;
import com.project.Taskmaster.Repository.UserRepository;
import com.project.Taskmaster.entity.Team;
import com.project.Taskmaster.entity.Users;



@Service
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private UserRepository UserRepo;
	
	public void createTeam(String teamName,Users TeamAdmin) {
		// TODO Auto-generated method stub
		Team newTeam= new Team(teamName);
		
		Set<Users> members=new HashSet<>() ;
		
		members.add(TeamAdmin);
		
		newTeam.setteamName(teamName);
		newTeam.setMembers(members);
		
		teamRepo.save(newTeam);
	}
	
	public boolean ifTeamExist(String teamName) {
		
		Team team= teamRepo.findByteamName(teamName);
		if(team==null)
				return false;
		
		return true;
	}
	
	
	public boolean ifUserExists(String userName) {
		
		Users member=UserRepo.findByUsername(userName);
		
		if(member==null ||!member.getisEnabled())
				return false;
		
		return true;
	}

	
	public String addMembersToTeam(String Username, String teamName) {
		// TODO Auto-generated method stub
		
		if(!ifTeamExist(teamName))
			return "Team doesnt exist";
		
		if(!ifUserExists(Username))
			return "member doesnt exist or is not verified ";
		
		
		Team team= teamRepo.findByteamName(teamName);
		Users member=UserRepo.findByUsername(Username);
		
		Set<Users>existing_members=team.getMembers();
		existing_members.add(member);
		team.setMembers(existing_members);
		teamRepo.save(team);
		
		return "added new Member";
	}

	public Set<String> showMembersOfTeam(String teamName) {
		
		
		Set<String>memberNames=new HashSet<>();
		Team team= teamRepo.findByteamName(teamName);
		
		if(!ifTeamExist(teamName))
			return null;
		
		
		Set<Users>member= team.getMembers();
		for(Users user: member) {
			memberNames.add(user.getusername());
		}
		
		return memberNames;
	}
	

}
