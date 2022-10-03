package com.blogapp.app.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	
	@NotEmpty
	private String categoryTitle;
	
	@NotEmpty()
	@Size(min=5, max=100, message="description should be min of 5 charactr and less then 100")
	private String categoryDescription;
}
