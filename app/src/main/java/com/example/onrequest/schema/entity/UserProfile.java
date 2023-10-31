package com.example.onrequest.schema.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class UserProfile {

@PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String photo;
    private String mail;

    public UserProfile(long id, String name, String photo, String mail) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
