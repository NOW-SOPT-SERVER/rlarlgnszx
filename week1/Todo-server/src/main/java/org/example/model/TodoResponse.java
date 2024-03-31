package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private String url;

    public TodoResponse(TodoEntity todoEntity){
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.order = todoEntity.getOrder();
        this.completed =todoEntity.getCompleted();
        //원래 이렇게 짜면 하드코딩됌

        this.url = "http://localhost:8080/" + this.id;
    }
}
