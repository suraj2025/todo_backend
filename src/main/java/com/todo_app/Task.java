
package com.todo_app;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
public class Task {
	@Id
    private String id;
    private String text;
    private boolean completed=false;

    // Constructors
    public Task() {}
    

    // Getters and Setters
    

    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
