package com.sb.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.blog.entity.User;
import com.sb.blog.exception.ResourceNotFoundException;
import com.sb.blog.payloads.UserDto;
import com.sb.blog.repository.UserRepository;
import com.sb.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User dtoToUser = this.dtoToUser(userDto);
		User save = this.userRepository.save(dtoToUser);

		return this.userTODto(save);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", id));
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassward(userDto.getUserPassward());
		user.setUserAbout(userDto.getUserAbout());

		User updatedUser = this.userRepository.save(user);
		UserDto userTODto = this.userTODto(updatedUser);

		return userTODto;
	}

	@Override
	public UserDto getUserById(int id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", id));

		UserDto userTODto = this.userTODto(user);

		return userTODto;
	}

	@Override
	public List<UserDto> getAllUser() {

		List<User> userList = this.userRepository.findAll();
		List<UserDto> userDtos = userList.stream().map(user -> this.userTODto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(int id) {

		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", id));

		this.userRepository.delete(user);
	}

	private User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		return user;
		/*
		 * User user = new User(); user.setUserId(userDto.getUserId());
		 * user.setUserName(userDto.getUserName());
		 * user.setUserEmail(userDto.getUserEmail());
		 * user.setUserPassward(userDto.getUserPassward());
		 * user.setUserAbout(userDto.getUserAbout()); return user;
		 */

	}

	private UserDto userTODto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;

		/*
		 * UserDto userDto = new UserDto(); userDto.setUserId(user.getUserId());
		 * userDto.setUserName(user.getUserName());
		 * userDto.setUserEmail(user.getUserEmail());
		 * userDto.setUserPassward(user.getUserPassward());
		 * userDto.setUserAbout(user.getUserAbout()); return userDto;
		 */
	}

}
