package net.yorksolutions.memomembackend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThreadList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String userCreated;
    String invitedUser;
    String title;
    String date;

    public ThreadList(String userCreated, String invitedUser, String title, String date) {
        this.userCreated = userCreated;
        this.invitedUser = invitedUser;
        this.title = title;
        this.date = date;
    }

    public ThreadList() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getInvitedUser() {
        return invitedUser;
    }

    public void setInvitedUser(String invitedUser) {
        this.invitedUser = invitedUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
