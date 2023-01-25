package com.heoch.todoapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Todo") // name 명시하지 않으면 @Entity이름을 따라감 없으면 클래스명
public class TodoEntity {
    @Id // 기본키 지정
    @GeneratedValue(generator = "system-uuid") // ID자동생성, system-uuid : 문자열 형태의 UUID
    @GenericGenerator(name="system-uuid", strategy="uuid")
    private String id; // 이 오브젝트의 아이디
    private String userId; // 이 오브젝트를 생성한 사용자의 아이디
    private String title;   // 투두 타이틀
    private boolean done; // true - todo를 완료한 경우(checked)


}
