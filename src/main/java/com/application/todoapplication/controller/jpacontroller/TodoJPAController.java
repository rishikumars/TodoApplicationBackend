package com.application.todoapplication.controller.jpacontroller;

import com.application.todoapplication.models.Todo;
import com.application.todoapplication.services.jpaservice.TodoJPAService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin()
@RestController
public class TodoJPAController {
private  TodoJPAService todoService;

    public TodoJPAController(TodoJPAService todoService) {
        this.todoService = todoService;
    }
    @GetMapping("/jpa/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username)
    {
       return todoService.getAllTodos(username);
    }
    @GetMapping("/jpa/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id)
    {
        return todoService.getTodo(username,id);
    }
@PostMapping("/jpa/{username}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo){
        Todo createdTodo=todoService.saveTodo(username,todo);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}/todo/{id}").buildAndExpand(createdTodo.getName(),createdTodo.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @PutMapping("/jpa/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @RequestBody Todo todo){
        Todo updatedTodo=todoService.saveTodo(username, todo);
        return new ResponseEntity<>(updatedTodo,HttpStatus.OK);
    }

    @DeleteMapping("/jpa/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
        boolean isDeleted=todoService.deleteTodo(username, id);
        if(isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
