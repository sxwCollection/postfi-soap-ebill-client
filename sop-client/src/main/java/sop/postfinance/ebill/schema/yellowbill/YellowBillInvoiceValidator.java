package sop.postfinance.ebill.schema.yellowbill;

import com.postfinance.bill.Envelope;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import sop.postfinance.ebill.validator.Validator;

@NoArgsConstructor
public class YellowBillInvoiceValidator implements Validator<Envelope> {

    private final YellowBillInvoiceMarshaller yellowBillInvoiceMarshaller = new YellowBillInvoiceMarshaller();

    @Override
    public ImmutablePair<Boolean, String> validate(Envelope toValidate) {

        return yellowBillInvoiceMarshaller.validateYellowBill(toValidate);
    }
}
