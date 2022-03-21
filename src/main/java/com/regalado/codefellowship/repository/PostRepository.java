package com.regalado.codefellowship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.regalado.codefellowship.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>
{

}
