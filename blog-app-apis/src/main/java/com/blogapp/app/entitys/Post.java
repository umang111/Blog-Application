package com.blogapp.app.entitys;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue
	private int postId;
	
	@Column(name="post_title", length=100, nullable=false)
	private String title;
	
	@Column(name="post_content", length=10000)
	private String content;
	
	@Column(name="image_name")
	private String imageName;
	
	@Column(name="Date")
	private Date AddedDate;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category categorys;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User users;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	private List<Comment> comments;
	
}
