package com.hdfclife.factory;

import com.hdfclife.exception.UnknownPolicyTypeException;
import com.hdfclife.model.EndowmentPolicy;
import com.hdfclife.model.Policy;
import com.hdfclife.model.TermLifePoilcy;
import com.hdfclife.model.UlipPolicy;

public class PolicyFactory {

    // this is the factory method of policyFactory
    // here we will get to know the type of policy the customer selects based on his/her choice
    // which will be stored inside "type" field and return the object of that to create the policy for the customer

    public static Policy create(String policyNumber , String customerName , String type , int premium , String status) throws UnknownPolicyTypeException {
        switch(type.toUpperCase()){
            case "TERM" -> {
                return new TermLifePoilcy(policyNumber,customerName,type,premium,status);
            }
            case "ULIP" -> {
                return new UlipPolicy(policyNumber,customerName,type,premium,status);
            }
            case "ENDOWMENT" -> {
                return new EndowmentPolicy(policyNumber,customerName,type,premium,status);
            }
            default -> throw new UnknownPolicyTypeException("Unknown policy type");
        }
    }
}
