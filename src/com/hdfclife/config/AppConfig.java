package com.hdfclife.config;

// this is the thread-safe enum based singleton class
// for which only one instance is being utilized for the entire application
public enum AppConfig {

    // enum based single comfigurable instance of the config class
    // used to retrieve the company name and maxClaim amount
    INSTANCE;

    private final String companyName = "HDFC Life";
    private final int maxClaimAmount = 500000;

    public String getCompanyName() {
        return companyName;
    }

    public int getMaxClaimAmount() {
        return maxClaimAmount;
    }
}
