package com.example.jeremy.myapplication.MessageModule;

/**
 * Created by jeremyw on 21/12/2015.
 */
public class MessageInfo {

    private String title;
    private String description;
    private String imagePath;

    public MessageInfo(String n, String e, String i)
    {
        title = n;
        description = e;
        imagePath = i;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
