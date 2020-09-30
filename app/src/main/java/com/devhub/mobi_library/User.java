package com.devhub.mobi_library;

public class User {

    private int id;
    private String name;
    private String email;
    private String hint;
    private String password;
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String name) {
        this.hint = name;
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

    public void setNumber(String email) {
        this.number = email;
    }



    public String getNumber() {
        return number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}