package com.heoch.todoapplication.dto;


import com.heoch.todoapplication.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor // 변수없는 생성자
@AllArgsConstructor // 모든 변수 포함한 생성자
@Data // getter, setter 생략
public class TodoDTO {

    private String todoId;
    private String title;
    private boolean done;

    private Date regDt;

    // userId는 스프링 시큐리티를 이용해 인증 구현
    // 보안상 숨기기 위해 dto에 포함하지 않는다.

    public TodoDTO(final TodoEntity entity) {
        this.todoId = entity.getTodoId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
        this.regDt = entity.getRegDt();
    }

    public static TodoEntity toEntity(final TodoDTO dto) {
        return TodoEntity.builder()
                .todoId(dto.getTodoId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .regDt(dto.getRegDt())
                .build();
    }


}
