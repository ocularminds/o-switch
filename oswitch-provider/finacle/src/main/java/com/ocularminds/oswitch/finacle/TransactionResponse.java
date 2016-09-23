package com.ocularminds.oswitch.finacle;

public class TransactionResponse {

    private C24TRANRES C24TRANRES;

    public TransactionResponse(final C24TRANRES res) {
        this.C24TRANRES = res;
    }

    public C24TRANRES getC24TRANRES() {
        return C24TRANRES;
    }

    public void setC24TRANRES(C24TRANRES C24TRANRES) {
        this.C24TRANRES = C24TRANRES;
    }

    @Override
    public String toString() {
        return "ClassPojo [C24TRANRES = " + C24TRANRES + "]";
    }
}
