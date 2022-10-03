package com.blogapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapp.app.entitys.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
