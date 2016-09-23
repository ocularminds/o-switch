package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.core.CoreBankingProvider;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

public interface Processor {

    final String LEDGER_BALANCE = "ledger";
    final String AVAILABLE_BALANCE = "available";

    ISOMsg process() throws ISOException;

    default String accountType(String pcode) {
        return pcode.substring(2, 4);
    }

    default String transactionType(String pcode) {
        return pcode.substring(0, 2);
    }

    default boolean isValidPinOrAccessCode(String pin) {
        return true;
    }

}
