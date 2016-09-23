package com.ocularminds.oswitch.finacle;

public class FEE {

    private String DR_ACCT_NO;

    private String ID;

    private String AMOUNT;

    private String CR_ACCT_NO;

    public FEE(final String debit, final String credit, final String amount) {
        this.DR_ACCT_NO = debit;
        this.CR_ACCT_NO = credit;
        this.AMOUNT = amount;
    }

    public String getDR_ACCT_NO() {
        return DR_ACCT_NO;
    }

    public void setDR_ACCT_NO(final String debit) {
        this.DR_ACCT_NO = debit;
    }

    public String getID() {
        return ID;
    }

    public void setID(final String id) {
        this.ID = id;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(final String amount) {
        this.AMOUNT = amount;
    }

    public String getCR_ACCT_NO() {
        return CR_ACCT_NO;
    }

    public void setCR_ACCT_NO(final String credit) {
        this.CR_ACCT_NO = credit;
    }

    @Override
    public String toString() {
        return "ClassPojo [DR_ACCT_NO = " + DR_ACCT_NO + ", ID = " + ID + ", AMOUNT = " + AMOUNT
                + ", CR_ACCT_NO = " + CR_ACCT_NO + "]";
    }
}
