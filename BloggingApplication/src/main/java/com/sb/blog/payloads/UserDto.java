package com.sb.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {
	private Integer userId;

	@NotEmpty
	@Size(min = 4, message = "User Name must be Atleat 4 character")
	private String userName;

	@Email(message = "Email is not valid")
	private String userEmail;

	@NotEmpty
	@Size(min = 3, max = 10, message = " passward must be between 3 to 10")
//	@Pattern
	private String userPassward;

	@NotEmpty
	private String userAbout;

}
