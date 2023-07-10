package com.sb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.blog.payloads.ApiResponse;
import com.sb.blog.payloads.CommentDto;
import com.sb.blog.service.CommentsService;

@RestController
@RequestMapping("/api")
public class CommentsController {

	@Autowired
	private CommentsService commentsService;

	@PostMapping("/post/{postid}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentsDto,
			@PathVariable("postid") int postId) {

		return new ResponseEntity<CommentDto>(this.commentsService.createComment(commentsDto, postId), HttpStatus.OK);
	}

	@DeleteMapping("comments/{id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("id") int commentId) {

		return new ResponseEntity<ApiResponse>(new ApiResponse("comments has been Delete", false), HttpStatus.OK);
	}
}
