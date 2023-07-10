package com.sb.blog.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class PostDto {

	private Integer postId;
	@NotEmpty
	private String postTitle;
	@NotEmpty
	private String postContent;

	private String imageName;
	private Date postDate;
	private UserDto user;
	private CategoryDto category;
	private List<CommentDto> commenstList = new ArrayList<>();
}
