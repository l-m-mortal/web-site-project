package com.example.websiteproject.repository;

import com.example.websiteproject.models.Posts;
import org.springframework.data.repository.CrudRepository;

public interface IPostsRepository extends CrudRepository<Posts, Long> {

}
