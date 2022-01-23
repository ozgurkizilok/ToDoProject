package com.todo.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.study.dto.UserDto;
import com.todo.study.service.*;

@RestController
@RequestMapping("/users")
public class UserController {

	public String result = "";

	@Autowired
	private UserService userService;

	@PostMapping
	public String addUser(@RequestBody UserDto userDto) {

		try {
			userService.addUser(userDto);
			result = "Kullanıcı başarılı şekilde sisteme kayıt edildi.";

		} catch (Exception e) {
			result = "Kullanıcı kayıt ederken hata oluştu" + e.getMessage();
			System.out.println(e.getMessage());
		}

		return result;

	}

	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable String userId) {

		try {
			userService.deleteUser(userId);
			result = "Kullanıcı başarılı şekilde sistemden silindi";
		} catch (Exception e) {
			result = "Kullanıcı silinirken hata oluştu";
			System.out.println(e.getMessage());
		}

		return result;

	}

}
