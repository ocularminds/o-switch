package com.ocularminds.oswitch.common;

import java.io.File;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.*;

public final class XmlFile {

    Source source;
    String uri;

    public XmlFile(final Source src, String url) {
        this.source = src;
        this.uri = url;
    }

    public enum Source {

        FILE {

            @Override
            public Document parse(DocumentBuilder builder, String url) throws Exception {
                File f = new File(url);
                return builder.parse(f);
            }

        },
        STRING {

            @Override
            @SuppressWarnings("deprecation")
            public Document parse(DocumentBuilder builder, String url) throws Exception {
                StringBufferInputStream stream = new StringBufferInputStream(url);
                return builder.parse(stream);
            }

        },
        URI {

            @Override
            public Document parse(DocumentBuilder builder, String url) throws Exception {

                URL soapURI = new URL(url);
                java.io.InputStream is = soapURI.openStream();
                return builder.parse(is);
            }

        };

        public abstract Document parse(DocumentBuilder builder, String url) throws Exception;
    }

    public Document document() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = this.source.parse(builder, this.uri);
        doc.getDocumentElement().normalize();
        return doc;
    }

    public List<Element> read(final String obj, final Document doc) {
        List<Element> records = new ArrayList<>();
        try {
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName(obj);
            for (int x = 0; x < nodes.getLength(); x++) {
                Node node = nodes.item(x);
                if (node.getNodeType() == 1) {
                    Element data = (Element) node;
                    records.add(data);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return records;
    }

    public String valueOf(Document doc, String xpathExpression) {
        String value = "";
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            value = xpath.evaluate(xpathExpression, doc, XPathConstants.STRING).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;
    }

    public String valueOf(String tag, Element data) {
        NodeList nodes = data.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodes.item(0);
        return node != null ? node.getNodeValue() : null;
    }
}
