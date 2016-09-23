package com.ocularminds.oswitch.finacle;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the com.ocularminds.oswitch.finacle package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _SendTransaction_QNAME = new QName("http://finaclews.org",
      "sendTransaction");
  private final static QName _SendTransactionResponse_QNAME = new QName("http://finaclews.org",
      "sendTransactionResponse");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: com.ocularminds.oswitch.finacle
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link SendTransactionResponse }
   * 
   */
  public SendTransactionResponse createSendTransactionResponse() {
    return new SendTransactionResponse();
  }

  /**
   * Create an instance of {@link SendTransaction }
   * 
   */
  public SendTransaction createSendTransaction() {
    return new SendTransaction();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link SendTransaction }{@code >}
   * 
   */
  @XmlElementDecl(namespace = "http://finaclews.org", name = "sendTransaction")
  public JAXBElement<SendTransaction> createSendTransaction(SendTransaction value) {
    return new JAXBElement<SendTransaction>(_SendTransaction_QNAME, SendTransaction.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link SendTransactionResponse }{@code >}
   * 
   */
  @XmlElementDecl(namespace = "http://finaclews.org", name = "sendTransactionResponse")
  public JAXBElement<SendTransactionResponse> createSendTransactionResponse(
      SendTransactionResponse value) {
    return new JAXBElement<SendTransactionResponse>(_SendTransactionResponse_QNAME,
        SendTransactionResponse.class, null, value);
  }

}
