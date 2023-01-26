package com.heoch.todoapplication.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER_TB")
public class UserEntity {

    @Id
    @Column(name="user_id", length = 50)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid")
    private String userId; // 사용자에게 고유하게 부여되는 id

    @Column(name="user_name", nullable=false, length=20)
    private String userName; // 사용자의 이름

    @Column(name="email", nullable = false, length=50)
    private String email; // 사용자의 email, 아이디와 같은 기능을 한다.

    @Column(name="password", nullable = false, length=100)
    private String password; // 패스워드

}
