package sop.postfinance.ebill.starter.config;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

public class PasswordCallbackHandler implements CallbackHandler {

    public static String keyAlias;
    public static String keyStorePassWord;

    public PasswordCallbackHandler() {
    }

    public void handle(Callback[] callbacks) {
        for (Callback callback : callbacks) {
            WSPasswordCallback pc = (WSPasswordCallback) callback;
            if (keyAlias.equals(pc.getIdentifier())) {
                pc.setPassword(keyStorePassWord);
                return;
            }
        }
    }
}