package com.project.Taskmaster.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Taskmaster.entity.Task;



public interface TaskRepository extends JpaRepository<Task,Long>{

	public Optional<Task> findById(Long id);
	
}
