package com.project.Taskmaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Taskmaster.entity.VerificationToken;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationToken, Long>{

	VerificationToken findByToken(String token);

}
