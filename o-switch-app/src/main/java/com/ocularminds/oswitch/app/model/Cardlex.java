package com.ocularminds.oswitch.app.model;

import java.util.Date;

public class Cardlex {

    private String id;

    private double amount;

    private String token;

    private String account;

    private Date date;

    public String getId() {
        return token;
    }

    public void setId(final String xid) {
        this.id = xid;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(final double amt) {
        this.amount = amt;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ClassPojo [amount = " + amount + ", token = " + token + ", account = " + account
                + ", date = " + date + "]";
    }
}
