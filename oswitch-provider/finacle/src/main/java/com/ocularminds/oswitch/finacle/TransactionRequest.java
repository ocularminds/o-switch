package com.ocularminds.oswitch.finacle;

public class TransactionRequest {

    private C24TRANREQ C24TRANREQ;

    public TransactionRequest(final C24TRANREQ req) {
        this.C24TRANREQ = req;
    }

    public C24TRANREQ getC24TRANREQ() {
        return C24TRANREQ;
    }

    public void setC24TRANREQ(C24TRANREQ C24TRANREQ) {
        this.C24TRANREQ = C24TRANREQ;
    }

    @Override
    public String toString() {
        return "ClassPojo [C24TRANREQ = " + C24TRANREQ + "]";
    }
}
