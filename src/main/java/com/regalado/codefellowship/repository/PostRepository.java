package com.regalado.codefellowship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.regalado.codefellowship.model.PostModel;

public interface PostRepository extends JpaRepository<PostModel, Long>
{

}
