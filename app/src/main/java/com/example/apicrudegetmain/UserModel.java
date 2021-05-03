package com.example.apicrudegetmain;

public class UserModel {

    private String first_name;
    private String last_name;
    private Integer id;
    private String email;
    private String updatedAt;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public UserModel(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
