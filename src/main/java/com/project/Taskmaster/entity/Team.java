package com.project.Taskmaster.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	@ManyToMany
	private Set<Users> members;
	private String teamName;
	
	public Team() {};
	
	public Team( String teamName) {
		
		this.teamName = teamName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Users> getMembers() {
		return members;
	}
	public void setMembers(Set<Users> members) {
		this.members = members;
	}
	public String getteamName() {
		return teamName;
	}
	public void setteamName(String teamName) {
		this.teamName = teamName;
	}
	
	
	
	
}
