package com.heoch.todoapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="TODO_TB") // name 명시하지 않으면 @Entity이름을 따라감 없으면 클래스명
public class TodoEntity {
    @Id // 기본키 지정
    @Column(name = "todo_id", length = 50)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "system-uuid") // ID자동생성, system-uuid : 문자열 형태의 UUID
    @GenericGenerator(name="system-uuid", strategy="uuid")
    private String todoId; // 이 오브젝트의 아이디
    @Column(name="user_id", length = 50, nullable = false)
    private String userId; // 이 오브젝트를 생성한 사용자의 아이디
    @Column(name="title", length = 100, nullable = false)
    private String title;   // 투두 타이틀
    @Column(name="done", length = 1, nullable = false)
    private boolean done; // true - todo를 완료한 경우(checked)

    @Column(name="reg_dt", length=15, nullable = false)
    private Date regDt;


}
