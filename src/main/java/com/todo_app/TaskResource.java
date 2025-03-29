package com.todo_app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.Task;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskResource {
     @Autowired
     TaskRepo repo;
     @GetMapping
     List<Task> getAll(){
    	 
    	 return (List<Task>) repo.findAll();
     }
     
     
     
     @PostMapping
     public Task insert(@RequestBody Task task) {
    	 repo.save(task);
    	 return task;
     }
     
     @PutMapping("/{id}")
     public Optional<Task> updateStudent(@PathVariable String id, @RequestBody Task updatedTask) {
         return repo.findById(id).map(task -> {
             task.setText(updatedTask.getText());
             task.setCompleted(updatedTask.isCompleted());
             return repo.save(task);
         });
     }
     
     @DeleteMapping("/{id}")
     public void delete(@PathVariable String id) {
    	 
		repo.deleteById(id);
		
    	 
     }
}
