package com.technews.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@EntityScan
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user")

public class User implements Serializable {

    // instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @Transient
    boolean loggedIn;

    // table relationships
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;

    // need to use FetchType.LAZY to resolve multiple bags exception
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vote> votes;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    // what is this empty for?
    public User() {

    }

    // generate User class constructor
    public User(Integer id, String username, String email, String password, boolean loggedIn, List<Post> posts, List<Vote> votes, List<Comment> comments) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // generate getters and setters
    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public List<Post> getPosts() {
        return posts;
    }
    public List<Vote> getVotes() {
        return votes;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // generate equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isLoggedIn() == user.isLoggedIn()
                && Objects.equals(getId(), user.getId())
                && Objects.equals(getUsername(), user.getUsername())
                && Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getPassword(), user.getPassword())
                && Objects.equals(getPosts(), user.getPosts())
                && Objects.equals(getVotes(), user.getVotes())
                && Objects.equals(getComments(), user.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), isLoggedIn(), getPosts(), getVotes(), getComments());
    }
    // generate toString()

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                ", posts=" + posts +
                ", votes=" + votes +
                ", comments=" + comments +
                '}';
    }
}
