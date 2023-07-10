package com.sb.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryID;
	private String categoryTitle;
	private String categoryDescription;

//	one Category can Have Many Post
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> postList = new ArrayList<>();

}
