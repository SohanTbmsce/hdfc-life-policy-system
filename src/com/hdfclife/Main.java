package com.hdfclife;

import com.hdfclife.config.AppConfig;
import com.hdfclife.exception.InvalidClaimException;
import com.hdfclife.exception.UnknownPolicyTypeException;
import com.hdfclife.factory.PolicyFactory;
import com.hdfclife.model.Claim;
import com.hdfclife.model.Urgency;
import com.hdfclife.observer.BranchLetterNotifier;
import com.hdfclife.observer.ClaimEventPublisher;
import com.hdfclife.observer.InAppNotifier;
import com.hdfclife.service.AuditLogger;
import com.hdfclife.service.ClaimService;
import com.hdfclife.store.PolicyStore;
import com.hdfclife.strategy.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        // 1. printing the company name
        System.out.println("\n Company name : " + AppConfig.INSTANCE.getCompanyName());

        // 2. creatting the store
        PolicyStore policyStore = new PolicyStore();

        // 3. creating poliocies using policyStore
        policyStore.addPolicy(PolicyFactory.create("HDFC-LIFE-1001", "Anita Sharma", "TERM", 18500, "Active"));
        policyStore.addPolicy(PolicyFactory.create("HDFC-LIFE-1002", "Rahul Mehta", "ULIP", 42000, "Active"));
        policyStore.addPolicy(PolicyFactory.create("HDFC-LIFE-1003", "Priya Nair", "ENDOWMENT", 27000, "Active"));
        policyStore.addPolicy(PolicyFactory.create("HDFC-LIFE-1004", "Vikram Singh", "TERM", 15200, "Active"));
        policyStore.addPolicy(PolicyFactory.create("HDFC-LIFE-1005", "Sneha Patel", "ULIP", 36000, "Active"));
        policyStore.addPolicy(PolicyFactory.create("HDFC-LIFE-1006", "Anita Sharma", "ENDOWMENT", 22000, "Pending"));

        // 4. printing all policies using iterator
        System.out.println("\n ===========================");
        System.out.println("      Policy Details :         ");
        System.out.println(" ===========================");
        policyStore.printAllPolicies();

        // 5. printing unique customer count

        System.out.println("\n Unique customers count : " + policyStore.getUniqueCustomerCount());

        // 6. Lookup of a policy detail

        System.out.print("\n lookup of policy - HDFC-LIFE-1003 : ");
        System.out.println(policyStore.findByPolicyNumber("HDFC-LIFE-1003").getCustomer());

        // 7. printing the policies in the sorted order

        System.out.println("\n Printing the policies in the sorted order : ");
        for(String policy : policyStore.getSortedPolicies().keySet()){
            System.out.println(policy);
        }

        // 8. Demonstration of strategy pattern

        System.out.println("\nThe premium amount to be paid for the respective policies with base amount 42000 are as follows : ");
        System.out.println("  ULIP Premium : " + new PremiumCalculator(new UlipPremuimStrategy()).calculate(42000));
        System.out.println("  ULIP Premium : " + new PremiumCalculator(new TermPremiumStrategy()).calculate(42000));
        System.out.println("  ULIP Premium : " + new PremiumCalculator(new EndowmentStrategy()).calculate(27000));

        // 9. Observer pattern Lookup

        System.out.println("\n");
        ClaimEventPublisher claimEventPublisher = new ClaimEventPublisher();
        claimEventPublisher.registerObserver(new BranchLetterNotifier());
        claimEventPublisher.registerObserver(new InAppNotifier());
        claimEventPublisher.notifyObservers(new Claim.Builder("HDFC-LIFE-1003",30000, Urgency.HIGH).build());
        AuditLogger auditLogger = new AuditLogger();
        ClaimService claimService = new ClaimService(policyStore,claimEventPublisher, auditLogger);

        // 10. Creating claims using builder

        Claim highClaim = new Claim.Builder("HDFC-LIFE-1001",25000, Urgency.HIGH)
                .hospitalName("Apollo Hospital").remarks("Hospitalization").build();
        Claim mediumClaim = new Claim.Builder("HDFC-LIFE-1002",20000,Urgency.MEDIUM).build();
        Claim lowClaim = new Claim.Builder("HDFC-LIFE-1004", 20000, Urgency.LOW).build();

        // 11. filing the claims using fileClaim of ClaimService Class

        claimService.fileClaim(highClaim);
        claimService.fileClaim(mediumClaim);
        claimService.fileClaim(lowClaim);

        // 12. updating the cliam status ( status observer )

        System.out.println("\n Updating the claim status of policyno : HDFC-LIFE-1001 ");
        claimService.updateStatus(highClaim , "APPROVED");

        System.out.println("\n Updated the claim status for highClaim : HDFC-LIFE-1001 ");
        System.out.println("\n Updated the claim status for mediumClaim : HDFC-LIFE-1002 ");
        System.out.println("\n Updated the claim status for lowClaim : HDFC-LIFE-1004 ");

        // 14. reading the content of the audit.log using BufferReader
        System.out.println("\n Status of claim approvals : \n");
        String filePath = "audit.log";
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + filePath, e);
        }

        // 15. Exception handling use cases
        System.out.println("\n Exception handling use cases");
        try{
            Claim invalidClaim = new Claim.Builder("HDFC-LIFE-1006",600000,Urgency.MEDIUM).build();
            claimService.fileClaim(invalidClaim);
        }catch(InvalidClaimException e){
            System.out.println(e.getMessage());
        }
        try{
            PolicyFactory.create("HDFC-LIF1-1006","Anita Sharma","INVALID",20000,"PENDING");

        }catch(UnknownPolicyTypeException e){
            System.out.println(e.getMessage());
        }

        System.out.println("\n Application execution ended successfully !!");

    }
}