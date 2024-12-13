package sop.postfinance.ebill.schema.marshaller;

import lombok.NonNull;
import lombok.SneakyThrows;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.OutputStream;
import java.io.StringWriter;

/**
 * marshaller and unmarshaller with generic type
 *
 * @param <T> type of schema class
 */
public abstract class SchemaMarshaller<T> {

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    private Schema schema;

    /**
     * Creates a SchemaMarshaller
     *
     * @param formattedOutput
     * @param clazz           Jaxb schema class to marshal/unmarshal.
     */
    @SneakyThrows
    public SchemaMarshaller(boolean formattedOutput, @NonNull Class<T> clazz) {
        JAXBContext context = JAXBContext.newInstance(clazz);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);
        unmarshaller = context.createUnmarshaller();
    }

    /**
     * Creates a SchemaMarshaller
     */
    public SchemaMarshaller(@NonNull Class<T> clazz) {
        this(false, clazz);
    }

    /**
     * Marshal a schema object to XML String.
     *
     * @return XML
     */
    public String marshal(@NonNull T schemaObject) throws JAXBException {
        StringWriter stringWriter = new StringWriter(1024);
        marshaller.marshal(schemaObject, stringWriter);
        return stringWriter.toString();
    }

    /**
     * Marshal the schema object to outputStream.
     */
    public void marshal(@NonNull T schemaObject, @NonNull OutputStream outputStream) throws JAXBException {
        marshaller.marshal(schemaObject, outputStream);
    }


    /**
     * Unmarshal from given streamSource to schema object
     *
     * @param streamSource
     * @return The schema object
     */
    public T unmarshal(@NonNull StreamSource streamSource) throws JAXBException {
        return (T) unmarshaller.unmarshal(streamSource);
    }

    /**
     * initialize XML Schema
     *
     * @param schemaSource
     */
    @SneakyThrows
    public void initSchema(@NonNull Source schemaSource) {
        schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(schemaSource);
    }

    /**
     * Validate and marshal xml object. Validation may reduce the performance, pls avoid to use it in the production.
     *
     * @param schemaObject
     * @return XML
     */
    public String validateMarshal(@NonNull T schemaObject) throws JAXBException {

        marshaller.setSchema(schema);
        StringWriter stringWriter = new StringWriter(1024);
        marshaller.marshal(schemaObject, stringWriter);
        marshaller.setSchema(null);
        return stringWriter.toString();
    }

}
