package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.model.TodoResponse;
import org.example.service.TodoService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class Controller {
    private final TodoService service;
    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request){
        System.out.println("CREATE");
        if (ObjectUtils.isEmpty(request.getTitle()))
            return (ResponseEntity<TodoResponse>) ResponseEntity.badRequest();

        if(ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);
        if(ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);

        TodoEntity result = this.service.add(request);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
        System.out.println("READ ONE");
        TodoEntity result = this.service.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>>readAll(){
        System.out.println("READ ALL");
        List<TodoEntity> todolist = this.service.searchAll();
        List<TodoResponse> response = todolist.stream()
                .map(TodoResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest todorequest){
        System.out.println("UPDATE");
        TodoEntity todo = this.service.updateById(id,todorequest);

        return ResponseEntity.ok(new TodoResponse(todo));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<TodoResponse> deleteOne(@PathVariable Long id){
        System.out.println("DELTED ONE");
        this.service.deletebyId(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        System.out.println("DELETE ALL");
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
