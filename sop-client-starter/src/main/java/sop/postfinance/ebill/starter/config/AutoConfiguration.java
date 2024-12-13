package sop.postfinance.ebill.starter.config;

import b2bservice.ebill.swisspost.ch.B2BService;
import b2bservice.ebill.swisspost.ch.B2BService_Service;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Configuration
public class AutoConfiguration {

    private static final String PROPERTIES_PREF = "sop.ebill.client";

    @Bean
    @ConfigurationProperties(prefix = PROPERTIES_PREF)
    public EBillSoapClientProperties eBillSoapClientProperties() {

        return new EBillSoapClientProperties();
    }

    @Bean
    public B2BService eBillClient(@NonNull EBillSoapClientProperties eBillSoapClientProperties) throws MalformedURLException {

        PasswordCallbackHandler.keyAlias = eBillSoapClientProperties.getKeyAlias();
        PasswordCallbackHandler.keyStorePassWord = eBillSoapClientProperties.getKeyStorePassword();
        B2BService_Service service = new B2BService_Service(new URL(eBillSoapClientProperties.getUri()), B2BService_Service.SERVICE);
        B2BService b2BService = service.getUserCertificate();

        Map<String, Object> requestCtx = ((BindingProvider) b2BService).getRequestContext();
        requestCtx.put("security.signature.username", eBillSoapClientProperties.getKeyAlias());
        return b2BService;
    }
}
