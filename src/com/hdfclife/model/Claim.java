package com.hdfclife.model;

public class Claim {

    // required fields for claim

    private final String policyNo;
    private final int claimAmount;
    private final Urgency urgency;
    private final String hospitalName;
    private final String remarks;
    private String status;

    private Claim(Builder builder) {
        this.policyNo = builder.policyNo;
        this.claimAmount = builder.claimAmount;
        this.urgency = builder.urgency;
        this.hospitalName = builder.hospitalName;
        this.remarks = builder.remarks;
        this.status = "SUBMITTED";
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public int getClaimAmount() {
        return claimAmount;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {

        private final String policyNo;
        private final int claimAmount;
        private final Urgency urgency;

        private String hospitalName;
        private String remarks;

        public Builder(String policyNo, int claimAmount, Urgency urgency) {
            this.policyNo = policyNo;
            this.claimAmount = claimAmount;
            this.urgency = urgency;
        }

        public Builder hospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
            return this;
        }

        public Builder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        public Claim build() {
            return new Claim( this );

        }
    }

}
