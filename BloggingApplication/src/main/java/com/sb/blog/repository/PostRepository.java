package com.sb.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.blog.entity.Category;
import com.sb.blog.entity.Post;
import com.sb.blog.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByPostTitleContaining(String title);
//	if the inbuilt method will not work then writ query.
//	@Query("select p from Post p where p.posttitle :key")
//	List<Post> findByPostTitleContaining(@Param("key") String title);

}
