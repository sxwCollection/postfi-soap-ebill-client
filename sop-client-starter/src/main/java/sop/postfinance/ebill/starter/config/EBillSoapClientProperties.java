package sop.postfinance.ebill.starter.config;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EBillSoapClientProperties {

    /**
     * shcema location of a soap web service, for instance:
     * https://ebill.postfinance.ch/B2BService/B2BService.svc
     */
    @NotBlank
    private String uri;

    /**
     * The alias of keystore
     */
    @NotBlank
    private String keyAlias;

    /**
     * The password of keystore
     */
    @NotBlank
    private String keyStorePassword;
}
