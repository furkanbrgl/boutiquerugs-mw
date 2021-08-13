package com.boutiquerugsmw.model.appconfig;

public class BoutiqueRugsUser {

    private String email;
    private String password;

    @Override
    public String toString() {
        return "BoutiqueRugsUser{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
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
