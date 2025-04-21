package com.example.todo.todolist_api;

import com.example.todo.todolist_api.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<ToDoItem> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoItem> getTodobyId(@PathVariable Long id) {
        Optional<ToDoItem> todo = todoRepository.findById(id);
        return todo.map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ToDoItem> createTodo(@RequestBody ToDoItem todoItem) {
        ToDoItem savedTodo = todoRepository.save(todoItem);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoItem> updateTodo(@PathVariable Long id, @RequestBody ToDoItem updatedTodo) {
        Optional<ToDoItem> existingTodo = todoRepository.findById(id);
        return existingTodo.map(todo -> {
            updatedTodo.setId(id);
            ToDoItem savedTodo = todoRepository.save(updatedTodo);
            return new ResponseEntity<>(savedTodo, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}