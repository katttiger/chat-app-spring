package se.iths.cecilia.chatapp.model;

import java.time.LocalDateTime;

public class Message {

    private Long id;
    private User user;
    private String text;
    private LocalDateTime timestamp;

    public Message(User user, String text, LocalDateTime timestamp) {
        this.user = user;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Message(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
