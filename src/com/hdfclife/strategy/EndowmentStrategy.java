package com.hdfclife.strategy;

public class EndowmentStrategy implements PremiumStrategy {
    @Override
    public int calculate(int basePremium) {
        return ((basePremium * 108) / 100);
    }
}
