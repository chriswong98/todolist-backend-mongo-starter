package com.afs.todolist.service;

import com.afs.todolist.entity.Todo;
import com.afs.todolist.exception.InvalidIdException;
import com.afs.todolist.exception.TodoNotFoundException;
import com.afs.todolist.repository.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    //PUT POST DELETE
//    public Todo updateTodoStatus(String id){
//        Todo upgradTodo
//    }

    private void validateObjectId(String id){
        if(!ObjectId.isValid(id)){
            throw new InvalidIdException(id);
        }
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(String todoId, Todo toUpdateTodo) {
        Todo existingCompany = todoRepository.findById(todoId).get();

        if (toUpdateTodo.getDone() != null) {
            existingCompany.setDone(toUpdateTodo.getDone());
        }
        if (toUpdateTodo.getText() != null) {
            existingCompany.setText(toUpdateTodo.getText());
        }

        return todoRepository.save(existingCompany);
    }

    public void delete(String id) {
        todoRepository.deleteById(id);
    }
}
