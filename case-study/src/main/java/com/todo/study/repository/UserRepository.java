package com.todo.study.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.todo.study.model.User;

@Repository
public interface UserRepository extends CouchbaseRepository<User, String> {

	User findByUsernameAndPassword(String username, String password);

}
