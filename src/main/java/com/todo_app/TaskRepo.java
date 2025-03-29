package com.todo_app;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.todo.model.Task;

public interface TaskRepo extends MongoRepository<Task, String> {

}
