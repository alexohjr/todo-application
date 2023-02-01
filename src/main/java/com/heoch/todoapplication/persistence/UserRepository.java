package com.heoch.todoapplication.persistence;

import com.heoch.todoapplication.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>, UserRepositoryCustom {
    UserEntity findByEmail(String email);
    Boolean existsByEmail(String email);
//    UserEntity findByEmailAndPassword(String email, String password);

}
