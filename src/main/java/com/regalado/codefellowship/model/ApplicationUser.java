package com.regalado.codefellowship.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String bio;

    @OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL)
    List<Post> userPosts;

//    @ManyToMany(mappedBy = "following")
//    Set<ApplicationUser> users;
//
//    @ManyToMany
//    @JoinTable(
//            name="accounts_to_following",
//            joinColumns = { @JoinColumn(name="account")},
//            inverseJoinColumns = {@JoinColumn(name="following")}
//    )
//    Set<ApplicationUser> following;

    protected ApplicationUser() {
        // JPA DEFAULT METHOD
    }

    public ApplicationUser(String username, String firstName, String lastName, String bio, String password) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;

    }


    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Override
    public String getUsername() {

        return username;
    }


    public void setUsername(String username) {

        this.username = username;
    }

    @Override
    public String getPassword() {

        return password;
    }
    public void setPassword(String password) {

        this.password = password;
    }

    public String getFirstName() {

        return firstName;
    }
    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }
    public String getLastName() {

        return lastName;
    }
    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getBio() {

        return bio;
    }
    public void setBio(String bio) {

        this.bio = bio;
    }

    public List<Post> getUserPosts() {

        return userPosts;
    }
    public void setUserPosts(List<Post> userPosts) {

        this.userPosts = userPosts;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}