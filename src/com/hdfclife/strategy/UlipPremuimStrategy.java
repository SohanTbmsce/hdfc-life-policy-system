package com.hdfclife.strategy;

public class UlipPremuimStrategy implements PremiumStrategy {
    @Override
    public int calculate(int basePremium) {
        return ((basePremium * 112) / 100);
    }
}
