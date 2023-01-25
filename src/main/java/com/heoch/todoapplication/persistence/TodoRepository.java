package com.heoch.todoapplication.persistence;

import com.heoch.todoapplication.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    // JpaRepository<T, ID> -> T:테이블에 매핑될 엔티티 클래스, ID:Genetic Type

    List<TodoEntity> findByUserId(String userId);

}
