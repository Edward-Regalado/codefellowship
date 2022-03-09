package com.regalado.codefellowship.controller;

import com.regalado.codefellowship.model.ApplicationUserModel;
import com.regalado.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String getHomePage(Principal p, Model m) {
        if(p != null){
            ApplicationUserModel personalAccount = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("personalUsername", personalAccount.getUsername());
            m.addAttribute("personalProfileImage", personalAccount.getProfileImage());
        }
        assert p != null;
        ApplicationUserModel otherAccount = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("username", otherAccount.getUsername());
        m.addAttribute("firstName", otherAccount.getFirstName());
        m.addAttribute("lastName", otherAccount.getLastName());
        m.addAttribute("bio", otherAccount.getBio());
        m.addAttribute("profileImage", otherAccount.getProfileImage());
        m.addAttribute("posts", otherAccount.getUserPosts());
        return "/profile";
    }

    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login.html";
    }

    @GetMapping("/registration")
    public String signUp(String username, String password)
    {
        return "registration.html";
    }

    @GetMapping("/user/{id}")
    public String userProfile(Model m, Principal p, @PathVariable String username)
    {
        if(p != null){
            ApplicationUserModel personalAccount = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("personalUsername", personalAccount.getUsername());
            m.addAttribute("personalProfileImage", personalAccount.getProfileImage());
        }
        ApplicationUserModel otherAccount = applicationUserRepository.findByUsername(username);
        m.addAttribute("username", otherAccount.getUsername());
        m.addAttribute("firstName", otherAccount.getFirstName());
        m.addAttribute("lastName", otherAccount.getLastName());
        m.addAttribute("bio", otherAccount.getBio());
        m.addAttribute("profileImage", otherAccount.getProfileImage());
        m.addAttribute("posts", otherAccount.getUserPosts());
        return "/profile";
    }

    @PostMapping("/registration")
    public RedirectView createNewUser(String username, String firstName, String lastName,  String bio, String password)
    {
        String hashedPassword = passwordEncoder.encode(password);
        ApplicationUserModel newUser = new ApplicationUserModel(username, firstName, lastName, bio, hashedPassword);
        applicationUserRepository.save(newUser);
        return new RedirectView("/login");
    }


}