package com.devhub.mobi_library;

public class Books {

    private int id;
    private String initial;
    private String surname;
    private String title;
    private String edition;
    private String isbn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String name) {
        this.initial = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String name) {
        this.surname = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String temp) {
        this.title = temp;
    }



    public String getEdition() {
        return edition;
    }

    public void setEdition(String email) {
        this.edition = email;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String temp) {
        this.isbn = temp;
    }
}