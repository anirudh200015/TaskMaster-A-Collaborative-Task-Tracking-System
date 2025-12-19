package com.project.Taskmaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Taskmaster.entity.Users;



@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	Users findByUsername(String username);
	

}