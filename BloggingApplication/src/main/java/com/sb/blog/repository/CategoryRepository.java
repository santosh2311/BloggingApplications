package com.sb.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.blog.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
