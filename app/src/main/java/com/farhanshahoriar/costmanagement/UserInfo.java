package com.farhanshahoriar.costmanagement;

public class UserInfo {
    String userName;
    int pCost;
    double balance;

    public UserInfo(String userName, int pCost, double balance) {
        this.userName = userName;
        this.pCost = pCost;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getpCost() {
        return pCost;
    }

    public void setpCost(int pCost) {
        this.pCost = pCost;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
