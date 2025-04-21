package com.example.todo.todolist_api;


import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="todos")
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String task;

    private boolean completed;

    public ToDoItem() {
    }

    public ToDoItem( String task, boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
