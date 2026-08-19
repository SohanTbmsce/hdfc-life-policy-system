package com.hdfclife.service;

import com.hdfclife.exception.PolicyServiceException;
import com.hdfclife.model.Claim;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuditLogger implements AutoCloseable{

    public void log(Claim claim) {
        try (
                PrintWriter printWriter = new PrintWriter(new FileWriter("audit.log", true))) {
                printWriter.println("claim filed : "
                + claim.getPolicyNo() + "|"
                + "amount : " + claim.getClaimAmount()
                + "|" + "urgency : " + claim.getUrgency() +"|" + "status : " + claim.getStatus());
        } catch (IOException e) {
            throw new PolicyServiceException("unable to file the cliam : " + e);
        }
    }
    @Override
    public void close() throws Exception {

    }
}
