package com.project.Taskmaster.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Taskmaster.Service.TeamService;
import com.project.Taskmaster.entity.Users;



@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;
	
	
	@PostMapping("/CreateTeam")
	public String CreateTeam(@RequestParam String TeamName) {
		
		Users TeamAdmin = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//String TeamAdmin="anirudh";
		
		teamService.createTeam(TeamName,TeamAdmin);
		return "Team created successfully";
	}
	
	@PostMapping("/addMember")
	public String addMembers(@RequestParam String name,@RequestParam String TeamName) {
		
		return teamService.addMembersToTeam(name,TeamName);
	}
	
	
}
