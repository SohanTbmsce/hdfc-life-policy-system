package com.hdfclife.store;

import com.hdfclife.model.Claim;
import com.hdfclife.model.Policy;

import java.util.*;

public class PolicyStore {

    // List to add policies of the customer
    private List<Policy> policies = new ArrayList<>();

    // to store unique customer names
    private Set<String> customerNames = new HashSet<>();

    // to map the policies based on the policy Number
    private Map<String, Policy> policyMap = new HashMap<>();

    // to sort the policies and to store them based on key-value order
    private TreeMap<String, Policy> sortedPolicies = new TreeMap<>();

    // to settle the claim based on urgency
    private PriorityQueue<Claim> claimQueue;


    public void addPolicy(Policy policy) {

        // this method add policies , store unique customer names ,
        // map the policies based on policy number and sort the policies based on unique id

        policies.add(policy);
        customerNames.add(policy.getCustomer());
        policyMap.put(policy.getPolicyNumber(), policy);
        sortedPolicies.put(policy.getPolicyNumber(), policy);
    }

    // find out the policy of the customer based on policyNumber
    public Policy findByPolicyNumber(String PolicyNumber) {
        return policyMap.get(PolicyNumber);
    }

    // to return the unique customer names from the collection
    public int getUniqueCustomerCount() {
        return customerNames.size();
    }

    public PolicyStore() {

        // Comparator here is used to settle the claim order based on urgency
        // which is like HIGH before MEDIUM before LOW

        Comparator<Claim> claimComparator = Comparator.comparingInt(claim -> {
            switch (claim.getUrgency()) {
                case HIGH -> {
                    return 1;
                }
                case MEDIUM -> {
                    return 2;
                }
                case LOW -> {
                    return 3;
                }
                default -> {
                    return 4;
                }
            }
        });
        claimQueue = new PriorityQueue<>(claimComparator);

}
    // used to add the claims obtained into priority queue using the "claim" object
    public void addClaim(Claim claim) {
        claimQueue.add(claim);
    }

    // printAllPolicies() , getSortedPolicies , pollClaim()

    // used to print the policies details of the customer
    public void printAllPolicies(){

        // Iterator is used here to iterate over the policy object
        // to print all the policy regarding details of the customer

        Iterator<Policy> iterator = policies.iterator();
        while(iterator.hasNext()){
            Policy policy = iterator.next();
            System.out.println(
                    policy.getPolicyNumber() + "|"
                    + policy.getCustomer() + "|"
                    + policy.getType() + "|"
                    + policy.getPremium() + "|"
                    + policy.getStatus()
            );
        }
    }


    // used to get policies from tree map which stores the policy details in key-value order
    public TreeMap<String , Policy > getSortedPolicies(){
        return sortedPolicies;
    }


    // to settle the claim based on urgency using poll method
    public Claim pollClaim(){
        return claimQueue.poll();
    }

}


