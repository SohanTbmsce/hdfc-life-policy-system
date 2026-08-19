package com.hdfclife.observer;

import com.hdfclife.model.Claim;

// This observer is mainly created in order to update the registered obsevers
// about the change in the claim status update
// ClaimEventPublisher acts as the subject while
// ClaimObserver here is treated as one method interface

public interface ClaimObserver {

    void onClaimUpdate(Claim claim);
}
