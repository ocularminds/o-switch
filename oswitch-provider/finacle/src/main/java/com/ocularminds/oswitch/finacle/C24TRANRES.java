package com.ocularminds.oswitch.finacle;

public class C24TRANRES {

    private String TRAN_DATE_TIME;

    private String ACTION_CODE;

    private String BALANCE_CURRENCY;

    private String COUNTRY_CODE;

    private String STAN;

    private String AVAILABLE_BALANCE;

    private String LEDGER_BALANCE;

    public String getTRAN_DATE_TIME() {
        return TRAN_DATE_TIME;
    }

    public void setTRAN_DATE_TIME(String TRAN_DATE_TIME) {
        this.TRAN_DATE_TIME = TRAN_DATE_TIME;
    }

    public String getACTION_CODE() {
        return ACTION_CODE;
    }

    public void setACTION_CODE(String ACTION_CODE) {
        this.ACTION_CODE = ACTION_CODE;
    }

    public String getBALANCE_CURRENCY() {
        return BALANCE_CURRENCY;
    }

    public void setBALANCE_CURRENCY(String BALANCE_CURRENCY) {
        this.BALANCE_CURRENCY = BALANCE_CURRENCY;
    }

    public String getCOUNTRY_CODE() {
        return COUNTRY_CODE;
    }

    public void setCOUNTRY_CODE(String COUNTRY_CODE) {
        this.COUNTRY_CODE = COUNTRY_CODE;
    }

    public String getSTAN() {
        return STAN;
    }

    public void setSTAN(String STAN) {
        this.STAN = STAN;
    }

    public String getAVAILABLE_BALANCE() {
        return AVAILABLE_BALANCE;
    }

    public void setAVAILABLE_BALANCE(String AVAILABLE_BALANCE) {
        this.AVAILABLE_BALANCE = AVAILABLE_BALANCE;
    }

    public String getLEDGER_BALANCE() {
        return LEDGER_BALANCE;
    }

    public void setLEDGER_BALANCE(String LEDGER_BALANCE) {
        this.LEDGER_BALANCE = LEDGER_BALANCE;
    }

    @Override
    public String toString() {
        return "ClassPojo [TRAN_DATE_TIME = " + TRAN_DATE_TIME + ", ACTION_CODE = " + ACTION_CODE
                + ", BALANCE_CURRENCY = " + BALANCE_CURRENCY + ", COUNTRY_CODE = " + COUNTRY_CODE
                + ", STAN = " + STAN + ", AVAILABLE_BALANCE = " + AVAILABLE_BALANCE
                + ", LEDGER_BALANCE = " + LEDGER_BALANCE + "]";
    }
}
