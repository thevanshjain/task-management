package com.vansh.spring.demo.dao;

import com.vansh.spring.demo.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

}
