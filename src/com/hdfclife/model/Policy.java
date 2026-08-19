package com.hdfclife.model;

public abstract class Policy {

    // fields to create a policy

    private String policyNumber;
    private String customer;
    private String type;
    private int premium;
    private String status;

    // constructor to set the private fields

    public Policy(String policyNumber, String customer, String type, int premium, String status) {
        this.policyNumber = policyNumber;
        this.customer = customer;
        this.type = type;
        this.premium = premium;
        this.status = status;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public String getType() {
        return type;
    }

    public int getPremium() {
        return premium;
    }

    public String getStatus() {
        return status;
    }
}
