package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//todo 리스트 목록에 아이템을 추가
//todo  리스트 목록 중 특정 아이템을 조회
//todo 리스트 전체 목록을 조회
//todo 리스트 목록 중 특정 아이템을 수정
//todo 리스트 목록 중 특정 아이템을 삭제
//todo 리스트 전체 목록을 삭제
@Service
//? 구현해야할 구체적인 기능을 작성해야함
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoEntity add(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());
        return this.todoRepository.save(todoEntity);
    }
    public TodoEntity searchById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        return null;
    }
    public List<TodoEntity> searchAll() {
        return  this.todoRepository.findAll();

    }
    public TodoEntity updateById(Long id,TodoRequest todoRequest){
        TodoEntity todoEntity = this.searchById(id);
        if (todoRequest.getTitle()!=null){
            todoEntity.setTitle((todoRequest.getTitle()));
        }
        if (todoRequest.getOrder()!=null){
            todoEntity.setOrder((todoRequest.getOrder()));
        }
        if (todoRequest.getCompleted()!=null){
            todoEntity.setCompleted((todoRequest.getCompleted()));
        }
        return this.todoRepository.save(todoEntity);
    }
    public void deletebyId(Long id) {
        this.todoRepository.deleteById(id);
    }
    public void deleteAll(){
        this.todoRepository.deleteAll();
    }

}
