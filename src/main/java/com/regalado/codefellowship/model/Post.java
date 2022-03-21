package com.regalado.codefellowship.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    public String content;
    public LocalDateTime timestamp;

    @ManyToOne
    ApplicationUser myUser;

    public Post() {
     // JPA DEFAULT CONSTRUCTOR
    }

    public Post(String content, ApplicationUser applicationUser, java.time.LocalDateTime timestamp)
    {
        myUser = applicationUser;
        this.content = content;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp()
    {

        return timestamp;
    }

    public String getContent()
    {

        return content;
    }

    public void setContent(String content)
    {

        this.content = content;
    }
}
