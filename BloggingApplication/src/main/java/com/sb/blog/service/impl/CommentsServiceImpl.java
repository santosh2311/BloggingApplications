package com.sb.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.blog.entity.Comments;
import com.sb.blog.entity.Post;
import com.sb.blog.exception.ResourceNotFoundException;
import com.sb.blog.payloads.CommentDto;
import com.sb.blog.repository.CommentsRepository;
import com.sb.blog.repository.PostRepository;
import com.sb.blog.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentsRepository commentsRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentsDto, int postId) {

		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		Comments comments = this.modelMapper.map(commentsDto, Comments.class);
		comments.setPost(post);
		Comments save = this.commentsRepository.save(comments);
		CommentDto commentDto = this.modelMapper.map(save, CommentDto.class);

		return commentDto;
	}

	@Override
	public void deleteComment(int commentId) {

		this.commentsRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", commentId));

		this.commentsRepository.deleteById(commentId);
	}

}
