package com.sb.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryDto {

	private Integer categoryID;

	@NotEmpty
	private String categoryTitle;
	@NotEmpty
	@Size(min = 10, message = " Description must be grater than 10")
	private String categoryDescription;

}
