package sop.postfinance.ebill.client;

import b2bservice.ebill.swisspost.ch.B2BService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of the Postfinance eBill client API
 */
@RequiredArgsConstructor
public class EBillClientImpl implements EBillClient {

    @NonNull
    private final B2BService b2BService;

    @Override
    public String executePing(String billerID) {
        return b2BService.executePing(billerID, null, null, null);
    }

}
