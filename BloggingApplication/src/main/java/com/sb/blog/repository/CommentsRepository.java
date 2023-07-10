package com.sb.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.blog.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

}
