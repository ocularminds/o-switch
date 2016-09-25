package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.core.CoreBankingProvider;
import com.ocularminds.oswitch.core.Entry;
import com.ocularminds.oswitch.core.Fault;
import com.ocularminds.oswitch.app.model.Transaction;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import java.math.BigDecimal;
import java.util.Map;

public class TransferProcessor implements Processor {

    CoreBankingProvider provider;
    ISOMsg request;
    Service service;

    public TransferProcessor(final CoreBankingProvider prov, final Service svc, final ISOMsg req) {
        this.provider = prov;
        this.request = req;
        this.service = svc;
    }

    public ISOMsg process() throws ISOException {

        String pcode = request.getString(3);
        String amount = request.getString(4);
        String ref = request.getString(11);
        String track2 = request.getString(35);
        String terminalId = request.getString(41);
        String debit = request.getString(102);
        String credit = request.getString(103);
        byte[] pinbytes = request.getBytes(52);
        if (pinbytes == null) {
            pinbytes = "0000".getBytes();
        }
        String pin = new String(pinbytes);
        if (isValidPinOrAccessCode(pin)) {

            double amt = new BigDecimal(amount).divide(new BigDecimal("100")).doubleValue();
            Entry entry = new Entry(debit, credit, amt);
            entry.setReference(ref);
            entry.setNarration("FAST CASH TRANSFER");

            Transaction tran = new Transaction();
            tran.setChannel(Transaction.Channel.ATM);

            Fault fault = provider.transfer(entry);
            request.set(39, fault.getError());
            if (fault.getError().equals("00")) {
                request.set(39, "00");
            }

            tran.setStatus(fault.getError());
            this.service.create(tran);

        } else {
            request.set(39, "51");
        }

        return request;
    }
}
