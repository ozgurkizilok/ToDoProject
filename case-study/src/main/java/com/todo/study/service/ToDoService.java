package com.todo.study.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.study.dto.ToDoDto;
import com.todo.study.model.ToDo;
import com.todo.study.model.User;
import com.todo.study.repository.ToDoRepository;

@Service
public class ToDoService {

	@Autowired
	private ToDoRepository todoRepository;

	public ToDoDto addTodo(User user, ToDoDto todoDto) {

		ToDo todo = new ToDo();
		todo.setContent(todoDto.getContent());
		todo.setUserId(user.getId());
		todoRepository.save(todo);
		return todoDto;

	}

	public void toggleTodoCompleted(User user, String todoId) throws Exception {

		ToDo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
		if (todo.getUserId().equals(user.getId()) && todo.getCompleted() == false) { //bir kullanıcı başkasının todosunu yapamamalı.
			todo.setCompleted(!todo.getCompleted());
			todoRepository.save(todo);
		}
	}

	public void deleteTodo(String todoId,String userId) {
		ToDo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
		if(todo.getUserId().equals(userId)) { //bir kullanıcı başkasının todosunu silememeli.
			todoRepository.delete(todo);
		}
	}

	public List<ToDo> findAllByUserId(String userId) {
		return todoRepository.findAllByUserId(userId);
	}

}
