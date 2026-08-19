package com.hdfclife.observer;

import com.hdfclife.model.Claim;

import java.util.ArrayList;
import java.util.List;


public class ClaimEventPublisher {

    // this will keep track of all the observers
    public final List<ClaimObserver> observers = new ArrayList<>();

    // used to register the observers which notify the claim status update
    public void registerObserver(ClaimObserver observer) {
        observers.add(observer);
    }

    // used to call onClaimUpdate method whenever the claim update occurs.
    public void notifyObservers(Claim claim) {
        for (ClaimObserver observer : observers) {
            observer.onClaimUpdate(claim);
        }
    }

}
