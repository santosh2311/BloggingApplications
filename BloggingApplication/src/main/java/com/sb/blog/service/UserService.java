package com.sb.blog.service;

import java.util.List;

import com.sb.blog.payloads.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto userDto);

	public UserDto updateUser(UserDto userDto, int id);
	
	public UserDto getUserById(int id);
	
	public List<UserDto> getAllUser();
	
	public void deleteUser(int id);
}
