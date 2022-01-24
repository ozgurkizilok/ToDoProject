package com.todo.study.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.study.dto.UserDto;
import com.todo.study.model.User;
import com.todo.study.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ToDoService toDoService;

	public UserDto addUser(UserDto userDto) {

		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		userRepository.save(user);
		return userDto;

	}

	public void deleteUser(String userId) {
		toDoService.deleteByUserId(userId);
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
		userRepository.delete(user);
	}
	
	public User findByUsernameAndPassword(String username,String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

}
