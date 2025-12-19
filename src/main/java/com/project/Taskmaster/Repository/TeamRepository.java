package com.project.Taskmaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Taskmaster.entity.Team;



@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	Team findByteamName(String teamName);

}
