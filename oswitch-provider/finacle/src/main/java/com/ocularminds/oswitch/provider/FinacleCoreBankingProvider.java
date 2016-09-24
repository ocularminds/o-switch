package com.ocularminds.oswitch.provider;

import com.ocularminds.oswitch.core.CoreBankingProvider;
import com.ocularminds.oswitch.core.Entry;
import com.ocularminds.oswitch.core.Fault;
import com.ocularminds.oswitch.common.XmlPojo;
import com.ocularminds.oswitch.finacle.C24Integrator;
import com.ocularminds.oswitch.finacle.C24IntegrationService;
import com.ocularminds.oswitch.finacle.C24TRANREQ;
import com.ocularminds.oswitch.finacle.C24TRANRES;
import com.ocularminds.oswitch.finacle.FEE;
import com.ocularminds.oswitch.finacle.TransactionRequest;
import com.ocularminds.oswitch.finacle.TransactionResponse;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinacleCoreBankingProvider implements CoreBankingProvider {

    C24Integrator integrator;
    XmlParserCreator parserCreator;
    SimpleDateFormat sdf;
    SimpleDateFormat stf;
    final String OSWITCH_SUSPENCE = "2042604150";
    final Logger logger = Logger.getLogger(FinacleCoreBankingProvider.class.getName());

    public FinacleCoreBankingProvider() {
        this.stf = new SimpleDateFormat("yyyyMMddhhmmss");
        this.stf = new SimpleDateFormat("yyyyMMdd");
        this.integrator = new C24IntegrationService().getC24IntegrationPort();
        this.parserCreator = new XmlParserCreator() {
            @Override
            public XmlPullParser createParser() {
                try {
                    return XmlPullParserFactory.newInstance().newPullParser();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        GsonXml gsonXml = new GsonXmlBuilder().setXmlParserCreator(this.parserCreator).create();
    }

    public Fault balance(final Entry entry) {
        Fault fault = new Fault();
        C24TRANREQ tran = new C24TRANREQ();
        tran.setPROCESSING_CODE("31");
        tran.setCOUNTRY_CODE("NGA");
        tran.setDR_ACCT_NUM(entry.getDebit());
        TransactionRequest req = new TransactionRequest(tran);
        try {
            XmlPojo request = new XmlPojo(tran, tran.getClass());
            String result = this.integrator.sendTransaction(request.xml());
            XmlPojo response = new XmlPojo(result, tran.getClass());
            TransactionResponse res = (TransactionResponse) response.object();
            C24TRANRES feeback = res.getC24TRANRES();
            String error = feeback.getACTION_CODE();
            String ref = feeback.getSTAN();
            String balance = feeback.getAVAILABLE_BALANCE();
            if (error.equals("000")) {
                fault.setError("00");
                Map<String, Double> data = new HashMap<String, Double>();
                data.put("available_balance", new Double(feeback.getAVAILABLE_BALANCE()));
                data.put("ledger_balance", new Double(feeback.getLEDGER_BALANCE()));
                fault.setData(balance);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error processin transaction", ex);
        }

        return fault;
    }

    public Fault transfer(final Entry entry) {
        Fault fault = new Fault();
        C24TRANREQ tran = new C24TRANREQ();
        tran.setPROCESSING_CODE("50");
        tran.setTRAN_CRNCY_CODE("NGN");
        tran.setDR_ACCT_NUM(entry.getDebit());
        tran.setCR_ACCT_NUM(entry.getCredit());
        tran.setVALUE_DATE(this.stf.format(new Date()));
        tran.setTRAN_AMT(Double.toString(entry.getAmount()));
        tran.setRESERVED_FLD_1(entry.getNarration());
        // tran.setTerminal_NAME_LOCATION();
        tran.setTRAN_DATE_TIME(this.stf.format(new Date()));
        tran.setSTAN(entry.getReference());
        tran.setCOUNTRY_CODE("NGA");
        FEE fee = new FEE(entry.getDebit(), OSWITCH_SUSPENCE, "100");
        tran.setFEE(fee);
        TransactionRequest req = new TransactionRequest(tran);
        try {
            XmlPojo request = new XmlPojo(tran, tran.getClass());
            String result = this.integrator.sendTransaction(request.xml());
            XmlPojo response = new XmlPojo(result, tran.getClass());
            TransactionResponse res = (TransactionResponse) response.object();
            C24TRANRES feeback = res.getC24TRANRES();
            String error = feeback.getACTION_CODE();
            String ref = feeback.getSTAN();
            String balance = feeback.getAVAILABLE_BALANCE();
            if (error.equals("000")) {
                fault.setError("00");
                fault.setData(balance);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error processin transaction", ex);
        }
        return fault;
    }

    public Fault bill(final Entry entry) {
        Fault fault = new Fault();
        C24TRANREQ tran = new C24TRANREQ();
        tran.setPROCESSING_CODE("50");
        tran.setTRAN_CRNCY_CODE("NGN");
        tran.setDR_ACCT_NUM(entry.getDebit());
        tran.setCR_ACCT_NUM(entry.getCredit());
        tran.setVALUE_DATE(this.stf.format(new Date()));
        tran.setTRAN_AMT(Double.toString(entry.getAmount()));
        tran.setRESERVED_FLD_1(entry.getNarration());
        // tran.setTerminal_NAME_LOCATION();
        tran.setTRAN_DATE_TIME(this.stf.format(new Date()));
        tran.setSTAN(entry.getReference());
        tran.setCOUNTRY_CODE("NGA");
        FEE fee = new FEE(entry.getDebit(), OSWITCH_SUSPENCE, "100");
        tran.setFEE(fee);
        TransactionRequest req = new TransactionRequest(tran);
        try {
            XmlPojo request = new XmlPojo(tran, tran.getClass());
            String result = this.integrator.sendTransaction(request.xml());
            XmlPojo response = new XmlPojo(result, tran.getClass());
            TransactionResponse res = (TransactionResponse) response.object();
            C24TRANRES feeback = res.getC24TRANRES();
            String error = feeback.getACTION_CODE();
            String ref = feeback.getSTAN();
            String balance = feeback.getAVAILABLE_BALANCE();
            if (error.equals("000")) {
                fault.setError("00");
                fault.setData(balance);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error processin transaction", ex);
        }
        return fault;
    }
}
