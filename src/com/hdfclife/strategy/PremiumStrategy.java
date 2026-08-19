package com.hdfclife.strategy;

import com.hdfclife.model.UlipPolicy;

public interface PremiumStrategy {

    //this is basically the method used to calculate the amount required to
    // pay for the premium.
    int calculate(int basePremium);
}
