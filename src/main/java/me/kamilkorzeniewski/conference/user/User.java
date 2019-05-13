package me.kamilkorzeniewski.conference.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private int id;

    @Column(name="user_name",unique = true)
    private String name;

    @Column(name="user_email")
    private String email;

    public User() {
    }

    public boolean isEmailEqual(String email){
        return this.email.equals(email);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
