package com.example.shopinindia.Entity;

public class UserModel {
    private String  email;
    private String  course;
    private String password;

    public UserModel() {
    }

    public UserModel(String email, String course, String password) {
        this.email = email;
        this.course = course;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
