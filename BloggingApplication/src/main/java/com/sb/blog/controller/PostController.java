package com.sb.blog.controller;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sb.blog.config.AppConstants;
import com.sb.blog.entity.Post;
import com.sb.blog.payloads.ApiResponse;
import com.sb.blog.payloads.PostDto;
import com.sb.blog.payloads.PostResponse;
import com.sb.blog.repository.PostRepository;
import com.sb.blog.service.FileService;
import com.sb.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PostRepository postRepository;

	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")

	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") int userId,
			@PathVariable("categoryId") int categoryId) {

		return new ResponseEntity<PostDto>(this.postService.createPost(postDto, userId, categoryId),
				HttpStatus.CREATED);

	}

	@GetMapping("/user/{id}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("id") int id) {
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByUser(id), HttpStatus.OK);
	}

	@GetMapping("/category/{id}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") Integer id) {
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByCategory(id), HttpStatus.OK);

	}

	@GetMapping("/post/getall")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy

	) {

		return new ResponseEntity<PostResponse>(this.postService.getAllPost(pageNumber, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/post/getpostbyid/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") int id) {
		return new ResponseEntity<PostDto>(this.postService.getPostById(id), HttpStatus.OK);
	}

	@DeleteMapping("post/delete/{id}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable("id") int id) {
		this.postService.deletePostById(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Has been delete", false), HttpStatus.OK);
	}

	@GetMapping("post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keyword") String keyword) {
		return new ResponseEntity<List<PostDto>>(this.postService.searchPost(keyword), HttpStatus.OK);
	}

//	post image file
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> UploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") int postId) throws IOException {
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.UploadImage(path, image);

		postDto.setImageName(fileName);

		Post post = this.modelMapper.map(postDto, Post.class);

		Post save = this.postRepository.save(post);
		PostDto map = this.modelMapper.map(save, PostDto.class);
		return new ResponseEntity<PostDto>(map, HttpStatus.OK);
	}

}