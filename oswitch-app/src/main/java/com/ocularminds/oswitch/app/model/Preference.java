package com.ocularminds.oswitch.app.model;

public class Preference {

    private String id;

    private double amount;

    private String token;

    private String account;

    private String language;

    public String getId() {
        return id;
    }

    public void setId(final String xid) {
        this.id = xid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "ClassPojo [amount = " + amount + ", token = " + token + ", account = " + account
                + ", language = " + language + "]";
    }
}
