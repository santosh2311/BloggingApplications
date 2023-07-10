package com.sb.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sb.blog.entity.Category;
import com.sb.blog.entity.Post;
import com.sb.blog.entity.User;
import com.sb.blog.exception.ResourceNotFoundException;
import com.sb.blog.payloads.PostDto;
import com.sb.blog.payloads.PostResponse;
import com.sb.blog.repository.CategoryRepository;
import com.sb.blog.repository.PostRepository;
import com.sb.blog.repository.UserRepository;
import com.sb.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "userId", userId));

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setPostDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post save = this.postRepository.save(post);
		PostDto dto = this.modelMapper.map(save, PostDto.class);
		return dto;

	}

	@Override
	public PostDto getPostById(int id) {
		Post post = this.postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		System.out.print(postDto);

		return postDto;
	}

	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy) {

		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

		Page<Post> pagePost = this.postRepository.findAll(p);
		List<Post> postlist = pagePost.getContent();

		List<PostDto> postDto = postlist.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		System.out.println(postDto);
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;

	}

	@Override
	public void deletePostById(int id) {
		Post post = this.postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));

		this.postRepository.delete(post);

	}

	@Override
	public List<PostDto> getPostByCategory(int id) {

		Category category = this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", id));
		List<Post> postList = this.postRepository.findByCategory(category);

		List<PostDto> postDtoList = postList.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtoList;
	}

	@Override
	public List<PostDto> getPostByUser(int id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", id));

		List<Post> postList = this.postRepository.findByUser(user);

		List<PostDto> postDtoList = postList.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtoList;
	}

	@Override
	public List<PostDto> searchPost(String keyord) {

		List<Post> titleContainingPost = this.postRepository.findByPostTitleContaining(keyord);
//				List<Post> titleContainingPost = this.postRepository.findByPostTitleContaining("%"+keyord+"%");// for inbuilt query method in PostRespository line no. 22 to 24 

		List<PostDto> postDto = titleContainingPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}

}
