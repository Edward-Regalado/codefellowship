package com.regalado.codefellowship.controller;

import com.regalado.codefellowship.model.ApplicationUserModel;
import com.regalado.codefellowship.model.PostModel;
import com.regalado.codefellowship.repository.ApplicationUserRepository;
import com.regalado.codefellowship.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController
{
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/post")
    public RedirectView createPost(String username, String content)
    {
        ApplicationUserModel appUser = applicationUserRepository.findByUsername(username);
        LocalDateTime timestamp = LocalDateTime.now();
        PostModel newPost = new PostModel(content, appUser, timestamp);
        List<PostModel> posts = appUser.getUserPosts();
        if(posts == null){
            posts = new ArrayList<>();
        }
        posts.add(newPost);
        applicationUserRepository.save(appUser);
        String route = "/user/" + username;
        return new RedirectView(route);
    }

}
