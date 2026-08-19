package com.hdfclife.service;

import com.hdfclife.config.AppConfig;
import com.hdfclife.exception.InvalidClaimException;
import com.hdfclife.exception.PolicyNotFoundException;
import com.hdfclife.model.Claim;
import com.hdfclife.model.Policy;
import com.hdfclife.observer.ClaimEventPublisher;
import com.hdfclife.store.PolicyStore;

public class ClaimService {
    private final PolicyStore policyStore;
    private final ClaimEventPublisher claimEventPublisher;
    public final AuditLogger auditLogger;
    public ClaimService(PolicyStore policyStore, ClaimEventPublisher claimEventPublisher, AuditLogger auditLogger) {
        this.policyStore = policyStore;
        this.claimEventPublisher = claimEventPublisher;
        this.auditLogger = auditLogger;
    }

    public void fileClaim(Claim claim) {
        Policy policy = policyStore.findByPolicyNumber(claim.getPolicyNo());

        // throw exception if policy is not found
        if (policy == null) {
            throw new PolicyNotFoundException("Policy Not Found : " + claim.getPolicyNo());
        }

        // if claim amount is 0 or exceeds the max claim amount then throw invalid exception
        if(claim.getClaimAmount() <= 0 || claim.getClaimAmount() > AppConfig.INSTANCE.getMaxClaimAmount()){
            throw new InvalidClaimException("Claim Amount Exceed Max Amount : " + AppConfig.INSTANCE.getMaxClaimAmount());
        }

        // add the claim to policyStore
        policyStore.addClaim(claim);

        // record in audit
        auditLogger.log(claim);

    }

    // updates the status of the claim and notify the observers(inAppNotification and BranchNotification)
    public void updateStatus(Claim claim, String status) {
        claim.updateStatus(status);
        claimEventPublisher.notifyObservers(claim);
        auditLogger.log(claim);
    }
}
