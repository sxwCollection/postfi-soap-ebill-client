package sop.postfinance.ebill.client;

/**
 * Postfinance eBill API
 */
public interface EBillClient {

    /**
     * run the ping methode, return billerID by success
     *
     * @param billerID required
     * @return the billerId
     */
    String executePing(String billerID);

}