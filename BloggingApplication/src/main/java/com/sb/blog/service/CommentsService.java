package com.sb.blog.service;

import com.sb.blog.payloads.CommentDto;

public interface CommentsService {

	CommentDto createComment(CommentDto commentsDto, int postId);

	void deleteComment(int commentId);

}
