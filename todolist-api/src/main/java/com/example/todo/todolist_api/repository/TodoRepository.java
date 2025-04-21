package com.example.todo.todolist_api.repository;

import com.example.todo.todolist_api.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<ToDoItem,Long> {

}
