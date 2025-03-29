package com.todo_app;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;



public interface TaskRepo extends MongoRepository<Task, String> {

}
