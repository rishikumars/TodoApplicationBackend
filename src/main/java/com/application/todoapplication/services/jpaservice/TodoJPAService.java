package com.application.todoapplication.services.jpaservice;

import com.application.todoapplication.models.Todo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoJPAService {
    private TodoJPARepository todoJPARepository;

    public TodoJPAService(TodoJPARepository todoJPARepository) {
        this.todoJPARepository = todoJPARepository;
    }
    public List<Todo> getAllTodos(String username){
        return todoJPARepository.getByName(username);
    }

    public Todo getTodo(String username,long id){
        return todoJPARepository.findById(id).get();
    }

    public Todo saveTodo(String username,Todo todo){
        todo.setName(username);
        todoJPARepository.save(todo);
        return todo  ;
    }

    public boolean deleteTodo(String username, long id) {
        if(todoJPARepository.getById(id)!=null){
            todoJPARepository.deleteById(id);
            return true;
        }
        return false;
    }
}
