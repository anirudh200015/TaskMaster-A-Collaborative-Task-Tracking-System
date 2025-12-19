package com.project.Taskmaster.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Taskmaster.Repository.TaskRepository;
import com.project.Taskmaster.entity.Status;
import com.project.Taskmaster.entity.Task;
import com.project.Taskmaster.entity.TaskDTO;




@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	
	public Task createTask(TaskDTO Task) {
		// TODO Auto-generated method stub
		
		Task newTask= new Task(Task.getTitle(),Task.getDescription(),Task.getDueDate());
		taskRepository.save(newTask);
		return newTask;
	}


	public List<Task> viewALL() {
		// TODO Auto-generated method stub
		List<Task> allTasks=taskRepository.findAll();
		
		return allTasks;
	}


	public String ChangeStatusToCompleted(Long id) {
		// TODO Auto-generated method stub
		
		Optional<Task> optionalTask=taskRepository.findById(id);
		
		if(optionalTask==null) {
			return "Task not found";
		}
		
		Task task=optionalTask.get();
		task.setStatus(Status.COMPLETED);
		taskRepository.save(task);
			
		return "Changed the status";
	}
	

}
