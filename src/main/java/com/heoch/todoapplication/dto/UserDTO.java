package com.heoch.todoapplication.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private String token;
    private String email;
    private String userName;
    private String password;
    private String id;
}
