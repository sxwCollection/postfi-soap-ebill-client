package sop.postfinance.ebill.client;

import b2bservice.ebill.swisspost.ch.B2BService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"ebill.client.uri=https://ebill-ki.postfinance.ch/B2BService/B2BServiceCert.svc?singleWsdl", "ebill.client.keyAlias=iAmAnApple", "ebill.client.keyStorePassword=12345"})
public class BootStartTest {

    @Autowired
    private B2BService b2BService;

    @Test
    public void eBillClient_notNull() {
        Assertions.assertThat(b2BService).isNotNull();
    }
}
