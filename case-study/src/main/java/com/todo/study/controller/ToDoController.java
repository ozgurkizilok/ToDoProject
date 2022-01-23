package com.todo.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.study.dto.ToDoDto;
import com.todo.study.model.ToDo;
import com.todo.study.model.User;
import com.todo.study.service.ToDoService;
import com.todo.study.service.UserService;

@RestController
@RequestMapping("/todos")
public class ToDoController {

	public String result = "";

	@Autowired
	private UserService userService;

	@Autowired
	private ToDoService todoService;

	@PostMapping("/{username}/{password}/todos")
	public String addTodo(@PathVariable String username, @PathVariable String password, @RequestBody ToDoDto todoDto) {

		try {

			User user = userService.findByUsernameAndPassword(username, password);
			if (user != null) {
				ToDo todo = new ToDo();
				todo.setContent(todoDto.getContent());
				todo.setUserId(user.getId());
				todoService.addTodo(user, todoDto);
				result = "Başarılı şekilde kayıt eklendi";
			} else {
				result = "Kullanıcı adı veya şifre hatalı";
			}

		} catch (Exception e) {
			result = "Yapılacak listesi eklenirken hata oluştu";
			System.out.println(e.getMessage());
		}

		return result;

	}

	@PostMapping("/toggle/{username}/{password}/{todoId}")
	public String toggleTodoCompleted(@PathVariable String username, @PathVariable String password,
			@PathVariable String todoId) {

		try {
			User user = userService.findByUsernameAndPassword(username, password);
			if (user != null) {

				todoService.toggleTodoCompleted(user, todoId);
				result = "Kullanıcı yapılacak listesini başarıyla tamamladı.";

			} else {
				result = "Kullanıcı adı veya şifre hatalı";
			}
		} catch (Exception e) {
			result = "Yapılacak listesi tamamlanırken hata oluştu";
			System.out.println(e.getMessage());
		}

		return result;
	}

	@DeleteMapping("{username}/{password}/todos/{todoId}")
	public String deleteTodo(@PathVariable String username, @PathVariable String password,
			@PathVariable String todoId) {

		try {
			User user = userService.findByUsernameAndPassword(username, password);
			if (user != null) {
				todoService.deleteTodo(todoId,user.getId());
				result = "Silme İşlemi Başarıyla Gerçekleşti";
			} else {
				result = "Kullanıcı adı veya şifre hatalı";
			}
		} catch (Exception e) {
			result = "Yapılacak listesi silinirken hata oluştu";
			System.out.println(e.getMessage());
		}

		return result;

	}

}
