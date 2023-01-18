package com.heoch.todoapplication.dto;


import com.heoch.todoapplication.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {

    private String id;
    private String title;
    private boolean done;

    // userId는 스프링 시큐리티를 이용해 인증 구현
    // 보안상 숨기기 위해 dto에 포함하지 않는다.

    public TodoDTO(final TodoEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }


}
