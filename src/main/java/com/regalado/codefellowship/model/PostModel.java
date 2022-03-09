package com.regalado.codefellowship.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    public String content;
    public LocalDateTime timestamp;

    @ManyToOne
    ApplicationUserModel myUser;

    protected PostModel()
    {

    }

    public PostModel(String content, ApplicationUserModel applicationUserModel, java.time.LocalDateTime timestamp)
    {
        myUser = applicationUserModel;
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
