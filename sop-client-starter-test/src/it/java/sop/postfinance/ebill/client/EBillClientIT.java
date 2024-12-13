package sop.postfinance.ebill.client;

import b2bservice.ebill.swisspost.ch.B2BService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {
        "ebill.client.uri=https://ebill-ki.postfinance.ch/B2BService/B2BServiceCert.svc?singleWsdl",
//        "ebill.client.keyAlias=bigfish", "ebill.client.keyStorePassword=hardpw12345"
})
class SoapClientTestIT {

    private final String billerID = "vipBillerID";

    @Autowired
    private B2BService b2BService;

    @Test
    void executePing() {

        final String actualId = b2BService.executePing(billerID, null, null, null);
        assertEquals(billerID, actualId);
    }
}
