package com.sb.blog.service;

import java.util.List;

import com.sb.blog.payloads.PostDto;
import com.sb.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, int userId, int categoryId);

	PostDto getPostById(int id);

	PostResponse getAllPost(int pageNumber, int pageSize, String sortBy);

	void deletePostById(int id);

	List<PostDto> getPostByCategory(int id);

	List<PostDto> getPostByUser(int id);

	List<PostDto> searchPost(String keyord);

}
