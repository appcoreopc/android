package com.example.jeremy.myapplication.FriendModule;

/**
 * Created by jeremyw on 21/12/2015.
 */
public class Friend {

    private String name;
    private String email;
    private String image;

    public Friend(String n, String e, String i)
    {
        name = n;
        email = e;
        image = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
