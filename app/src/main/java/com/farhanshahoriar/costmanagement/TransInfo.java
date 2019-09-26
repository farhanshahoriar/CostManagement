package com.farhanshahoriar.costmanagement;

public class TransInfo {
    private String date,descrip,username;
    int amount;

    public TransInfo() {

    }

    public TransInfo(String date, String descrip, String username, int amount) {
        this.date = date;
        this.descrip = descrip;
        this.username = username;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
