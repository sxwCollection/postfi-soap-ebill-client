package sop.postfinance.ebill.validator;

import org.apache.commons.lang3.tuple.ImmutablePair;


/**
 * Interface to validate a Postfinance SOAP envelope against it's XML-schema.
 *
 * @param <T> type of Postfinance SOAP envelope to validate
 */
public interface Validator<T> {

    /**
     * Validate soap object with soap schema.
     * Validation may cause performance problem, avoid to use it in the production.
     *
     * @param toValidate The soap object to validate.
     * @return is valid and error message
     */
    ImmutablePair<Boolean, String> validate(T toValidate);
}
