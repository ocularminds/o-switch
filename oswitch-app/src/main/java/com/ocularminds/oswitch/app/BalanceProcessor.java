package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.core.CoreBankingProvider;
import com.ocularminds.oswitch.core.Entry;
import com.ocularminds.oswitch.core.Fault;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import java.util.Map;

public class BalanceProcessor implements Processor {

    CoreBankingProvider provider;
    Service service;
    ISOMsg request;

    public BalanceProcessor(final CoreBankingProvider prov, final Service svc, final ISOMsg req) {
        this.provider = prov;
        this.request = req;
        this.service = svc;
    }

    public ISOMsg process() throws ISOException {

        String pcode = request.getString(3);
        String ref = request.getString(11);
        String debit = request.getString(102);
        byte[] pinbytes = request.getBytes(52);
        if (pinbytes == null) {
            pinbytes = "0000".getBytes();
        }
        String pin = new String(pinbytes);
        if (isValidPinOrAccessCode(pin)) {
            Entry entry = new Entry(debit, null, 0.00d);
            entry.getReference();
            Fault fault = provider.balance(entry);
            request.set(39, fault.getError());
            if (fault.getError().equals("00")) {
                Map<String, Double> balances = (Map) fault.getData();
                Double available = balances.get(AVAILABLE_BALANCE);
                Double ledger = balances.get(LEDGER_BALANCE);
                String availableAmountSign = available.doubleValue() < 0 ? "D" : "C";
                String ledgerAmountSign = ledger.doubleValue() < 0 ? "D" : "C";
                StringBuilder sb = new StringBuilder();
                sb.append(accountType(pcode));
                sb.append("02");// + +amount type(* 01 Account ledger balance* 02 available balance*
                                // 07 Destination Account ledger balance* 08 Destination Account
                                // available balance* 20 Amount remaining this cycle.* 40 Amount
                                // cash.* 42 Amount surcharge
                sb.append("NGNK");// +Currency Code N3+ Currency minor unit N1+
                sb.append(availableAmountSign);// +Amount Sign A1 - C for Credit, D for Debit
                sb.append(String.format("%012d", (int) (available.doubleValue() * 100)));// +Value
                                                                                         // amount
                // N12
                sb.append(accountType(pcode));
                sb.append("02");
                sb.append("NGNK");
                sb.append(ledgerAmountSign);
                sb.append(String.format("%012d", (int) (ledger.doubleValue() * 100)));
                request.set(54, sb.toString());
            }

        } else {
            request.set(39, "51");
        }

        return request;
    }
}
