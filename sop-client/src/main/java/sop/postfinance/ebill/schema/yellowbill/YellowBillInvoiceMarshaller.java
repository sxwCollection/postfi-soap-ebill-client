package sop.postfinance.ebill.schema.yellowbill;

import com.postfinance.bill.Envelope;
import org.apache.commons.lang3.tuple.ImmutablePair;
import sop.postfinance.ebill.schema.marshaller.SchemaMarshaller;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.net.URISyntaxException;

public class YellowBillInvoiceMarshaller extends SchemaMarshaller<Envelope> {

    private final String SCHEMA = "xsd/ybInvoice_V2.0.4.xsd";

    /**
     * {@inheritDoc}
     */
    public YellowBillInvoiceMarshaller(boolean formattedOutput) {
        super(formattedOutput, Envelope.class);
    }

    /**
     * {@inheritDoc}
     */
    public YellowBillInvoiceMarshaller() {
        super(Envelope.class);
    }

    public ImmutablePair<Boolean, String> validateYellowBill(Envelope yellowBill) {

        try {
            initSchema(new StreamSource(
                    new File(getClass().getClassLoader().getResource(SCHEMA).toURI())));
            validateMarshal(yellowBill);
        } catch (URISyntaxException | JAXBException e) {
            return new ImmutablePair<>(false, e.toString());
        }
        return new ImmutablePair<>(true, "");
    }
}
