package com.afs.todolist.controller;

import com.afs.todolist.controller.dto.TodoCreateRequest;
import com.afs.todolist.controller.mapper.TodoMapper;
import com.afs.todolist.entity.Todo;
import com.afs.todolist.exception.InvalidIdException;
import com.afs.todolist.service.TodoService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    List<Todo> getAll() {
        return todoService.findAll();
    }

    //@POST @PUT @DELETE
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Todo addTodo(@RequestBody TodoCreateRequest todoRequest) {
        Todo todo =  todoMapper.toEntity(todoRequest);
        return todoService.addTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable String id, @RequestBody TodoCreateRequest todoRequest) {
        if(!ObjectId.isValid(id)){
            throw new InvalidIdException(id);
        }
        Todo todo =  todoMapper.toEntity(todoRequest);

        return todoService.update(id, todo);
    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    void delete



}
