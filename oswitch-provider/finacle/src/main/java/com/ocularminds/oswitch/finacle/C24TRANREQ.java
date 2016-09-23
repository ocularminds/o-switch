package com.ocularminds.oswitch.finacle;

public class C24TRANREQ {

    private String TRAN_CRNCY_CODE;

    private String TRAN_DATE_TIME;

    private String VALUE_DATE;

    private String TRAN_AMT;

    private String COUNTRY_CODE;

    private String CR_ACCT_NUM;

    private String STAN;

    private String DR_ACCT_NUM;

    private String PROCESSING_CODE;

    private String RESERVED_FLD_1;

    private FEE FEE;

    public String getTRAN_CRNCY_CODE() {
        return TRAN_CRNCY_CODE;
    }

    public void setTRAN_CRNCY_CODE(String TRAN_CRNCY_CODE) {
        this.TRAN_CRNCY_CODE = TRAN_CRNCY_CODE;
    }

    public String getTRAN_DATE_TIME() {
        return TRAN_DATE_TIME;
    }

    public void setTRAN_DATE_TIME(String TRAN_DATE_TIME) {
        this.TRAN_DATE_TIME = TRAN_DATE_TIME;
    }

    public String getVALUE_DATE() {
        return VALUE_DATE;
    }

    public void setVALUE_DATE(String VALUE_DATE) {
        this.VALUE_DATE = VALUE_DATE;
    }

    public String getTRAN_AMT() {
        return TRAN_AMT;
    }

    public void setTRAN_AMT(String TRAN_AMT) {
        this.TRAN_AMT = TRAN_AMT;
    }

    public String getCOUNTRY_CODE() {
        return COUNTRY_CODE;
    }

    public void setCOUNTRY_CODE(String COUNTRY_CODE) {
        this.COUNTRY_CODE = COUNTRY_CODE;
    }

    public String getCR_ACCT_NUM() {
        return CR_ACCT_NUM;
    }

    public void setCR_ACCT_NUM(String CR_ACCT_NUM) {
        this.CR_ACCT_NUM = CR_ACCT_NUM;
    }

    public String getSTAN() {
        return STAN;
    }

    public void setSTAN(String STAN) {
        this.STAN = STAN;
    }

    public String getDR_ACCT_NUM() {
        return DR_ACCT_NUM;
    }

    public void setDR_ACCT_NUM(String DR_ACCT_NUM) {
        this.DR_ACCT_NUM = DR_ACCT_NUM;
    }

    public String getPROCESSING_CODE() {
        return PROCESSING_CODE;
    }

    public void setPROCESSING_CODE(String PROCESSING_CODE) {
        this.PROCESSING_CODE = PROCESSING_CODE;
    }

    public String getRESERVED_FLD_1() {
        return RESERVED_FLD_1;
    }

    public void setRESERVED_FLD_1(String RESERVED_FLD_1) {
        this.RESERVED_FLD_1 = RESERVED_FLD_1;
    }

    public FEE getFEE() {
        return FEE;
    }

    public void setFEE(FEE FEE) {
        this.FEE = FEE;
    }

    @Override
    public String toString() {
        return "ClassPojo [TRAN_CRNCY_CODE = " + TRAN_CRNCY_CODE + ", TRAN_DATE_TIME = "
                + TRAN_DATE_TIME + ", VALUE_DATE = " + VALUE_DATE + ", TRAN_AMT = " + TRAN_AMT
                + ", COUNTRY_CODE = " + COUNTRY_CODE + ", CR_ACCT_NUM = " + CR_ACCT_NUM
                + ", STAN = " + STAN + ", DR_ACCT_NUM = " + DR_ACCT_NUM + ", PROCESSING_CODE = "
                + PROCESSING_CODE + ", RESERVED_FLD_1 = " + RESERVED_FLD_1 + ", FEE = " + FEE + "]";
    }
}
