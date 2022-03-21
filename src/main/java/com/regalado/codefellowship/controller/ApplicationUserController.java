package com.regalado.codefellowship.controller;

import com.regalado.codefellowship.model.ApplicationUser;
import com.regalado.codefellowship.model.Post;
import com.regalado.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/")
    public String getHomePage(Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

            model.addAttribute("username", username);
        }
        return "index.html";
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

    @PostMapping("/registration")
    public RedirectView createNewUser(String username, String firstName, String lastName, String bio, String password)
    {
        String hashedPassword = passwordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(username, firstName, lastName, bio, hashedPassword);
        applicationUserRepository.save(newUser);
        authorizationServlet(username, password);
        return new RedirectView("/login");
    }

    @GetMapping("/myprofile")
    public String getUserProfile(Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
            model.addAttribute("username", applicationUser.getUsername());
            model.addAttribute("firstName", applicationUser.getFirstName());
            model.addAttribute("lastName", applicationUser.getLastName());
            model.addAttribute("bio", applicationUser.getBio());
            model.addAttribute("posts", applicationUser.getUserPosts());
        }
        return "/my-profile.html";
    }

    @GetMapping("/user/{username}")
    public String userProfile(Principal principal, Model model, @PathVariable String username)
    {
        if(principal != null){
            ApplicationUser personalAccount = applicationUserRepository.findByUsername(principal.getName());
            model.addAttribute("personalUsername", personalAccount.getUsername());
        }
        ApplicationUser otherAccount = applicationUserRepository.findByUsername(username);
        model.addAttribute("username", otherAccount.getUsername());
        model.addAttribute("firstName", otherAccount.getFirstName());
        model.addAttribute("lastName", otherAccount.getLastName());
        model.addAttribute("bio", otherAccount.getBio());
        model.addAttribute("posts", otherAccount.getUserPosts());
        return "/my-profile.html";
    }

    public void authorizationServlet(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException se) {
            System.out.println("Error");
            se.printStackTrace();
        }
    }

}
