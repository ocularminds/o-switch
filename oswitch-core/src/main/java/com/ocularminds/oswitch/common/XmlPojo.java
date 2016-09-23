package com.ocularminds.oswitch.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public final class XmlPojo {

    Object source;
    Class type;
    JAXBContext context;
    Unmarshaller unmarshaller;
    Marshaller marshaler;

    public XmlPojo(Object src, Class typ) throws Exception {
        this.source = src;
        this.type = typ;
        this.context = JAXBContext.newInstance(this.type);
    }

    public Object object() {
        Object obj = null;
        try {
            Unmarshaller unmarshaller = this.context.createUnmarshaller();
            obj = unmarshaller.unmarshal(new StringReader((String) this.source));
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
        return obj;
    }

    public String xml() {
        String result;
        StringWriter sw = new StringWriter();
        try {
            Marshaller marshaler = this.context.createMarshaller();
            marshaler.marshal(this.source, sw);
            result = sw.toString();
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

}
