package com.application.todoapplication.services.jpaservice;

import com.application.todoapplication.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TodoJPARepository extends JpaRepository<Todo, Long> {
    List<Todo> getByName(String name);
}

