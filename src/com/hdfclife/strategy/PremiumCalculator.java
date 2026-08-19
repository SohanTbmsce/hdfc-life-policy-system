package com.hdfclife.strategy;

public class PremiumCalculator {

    private PremiumStrategy premiumStrategy;

    public PremiumCalculator(PremiumStrategy premiumStrategy) {
        this.premiumStrategy = premiumStrategy;
    }

    public void setStrategy(PremiumStrategy premiumStrategy) {
        this.premiumStrategy = premiumStrategy;
    }

    public int calculate(int basePremium) {
        return premiumStrategy.calculate(basePremium);
    }

}
