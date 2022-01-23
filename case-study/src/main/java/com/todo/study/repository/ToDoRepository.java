package com.todo.study.repository;

import java.util.List;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.todo.study.model.ToDo;

@Repository
public interface ToDoRepository extends CouchbaseRepository<ToDo, String> {
	List<ToDo> findAllByUserId(String userId);
}
