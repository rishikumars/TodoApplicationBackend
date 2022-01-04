package com.application.todoapplication.services.mapservice;

import com.application.todoapplication.models.Todo;
import org.springframework.stereotype.Service;
import java.util.*;



@Service
public class TodoMapService{
   // private long idCounter;
    private  Map<Long,Todo> userTodoMap;
    private static Map<String,Long> lastMaxId=new HashMap<>();

    private static Map todoMap= new HashMap<String,Map<Long,Todo>>();
/*static {
    todoMap.put(++idCounter,new Todo(idCounter,"Learn Angular",new Date(),false));
}*/
    public List<Todo> getAllTodos(String username){
        if(todoMap.get(username)!=null)
        return  new ArrayList<Todo>(((Map<Long, Todo>) todoMap.get(username)).values());
     else
         return null;
    }

    public Todo getTodo(String username,long id){
        userTodoMap= (Map<Long, Todo>) todoMap.get(username);
        return userTodoMap.get(id);
    }

    public Todo saveTodo(String username,Todo todo){
        todo.setName(username);
        if(todoMap.get(username)==null){
            long idCounter=0;
            todo.setId(++idCounter);
            lastMaxId.put(username,idCounter);
            Map<Long,Todo> newTodoMap=new HashMap<>();
            newTodoMap.put(idCounter,todo);
            todoMap.put(username,newTodoMap);
        }
        else{
            userTodoMap=(Map<Long, Todo>) todoMap.get(username);
          int size=(userTodoMap.size());
            if(todo.getId()==0){
                long id=lastMaxId.get(username)+1;
                todo.setId(id);
                lastMaxId.put(username,id);
                userTodoMap.put(id,todo);
                todoMap.put(username,userTodoMap);
            }
            else
            {
                userTodoMap=(Map<Long, Todo>) todoMap.get(username);
                userTodoMap.put(todo.getId(), todo);
                todoMap.put(username,userTodoMap);
            }
        }
        return todo  ;
    }

    public boolean deleteTodo(String username, long id) {
        userTodoMap = (Map<Long, Todo>) todoMap.get(username);
        return userTodoMap.remove(id, userTodoMap.get(id));
    }
}
