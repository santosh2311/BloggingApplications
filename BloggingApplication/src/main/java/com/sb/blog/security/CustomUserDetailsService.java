package com.sb.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sb.blog.entity.User;
import com.sb.blog.exception.ResourceNotFoundException;
import com.sb.blog.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		Loading User From database by UserName

		User user = this.userRepository.findByUserEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userEmail", username));

		return user;
	}

}
