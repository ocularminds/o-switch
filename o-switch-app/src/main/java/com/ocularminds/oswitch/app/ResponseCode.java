package com.ocularminds.oswitch.core;

import java.util.HashMap;
import java.util.Map;

public class ResponseCode {

    static Map<String, String> codes;

    public static String response(String code) {
        return codes.get(code);
    }

    static {
        codes = new HashMap<String, String>();
        codes.put("00", "Successful approval/completion or that V.I.P. PIN verification is valid");
        codes.put("01", "Refer to card issuer");
        codes.put("02", "Refer to card issuer, special condition");
        codes.put("03", "Invalid merchant or service provider");
        codes.put("04", "Pickup card");
        codes.put("05", "Do not honor");
        codes.put("06", "Error");
        codes.put("07", "Pickup card, special condition");
        codes.put("10", "Partial Approval");
        codes.put("51", "V.I.P. approval");
        codes.put("12", "Invalid transaction");
        codes.put("13", "Invalid amount ");
        codes.put("14", "Invalid account number");
        codes.put("15", "No such issuer");
        codes.put("17", "Customer cancellation");
        codes.put("19", "Re-enter transaction");
        codes.put("20", "Invalid response");
        codes.put("21", "No action taken ");
        codes.put("22", "Suspected Malfunction");
        codes.put("25", "Unable to locate record in file");
        codes.put("28", "File is temporarily unavailable");
        codes.put("30", "Format Error");
        codes.put("41", "Pickup card (lost card)");
        codes.put("43", "Pickup card (stolen card)");
        codes.put("51", "Insufficient funds");
        codes.put("52", "No checking account");
        codes.put("53", "No savings account");
        codes.put("54", "Expired card");
        codes.put("55", "Incorrect PIN");
        codes.put("57", "Transaction not permitted to cardholder");
        codes.put("58", "Transaction not allowed at terminal");
        codes.put("59", "Suspected fraud");
        codes.put("61", "Activity amount limit exceeded");
        codes.put("62", "Restricted card");
        codes.put("63", "Security violation");
        codes.put("65", "Activity count limit exceeded");
        codes.put("68", "Response received too late");
        codes.put("75", "Allowable number of PIN-entry tries exceeded");
        codes.put("76", "Unable to locate previous message");
        codes.put("77", "Inconsistent transaction data with original message");
        codes.put(
                "78",
                "’Blocked, first used’—The transaction is from a new cardholder, and the card has not been properly unblocked.");
        codes.put("80", "Visa transactions: credit issuer unavailable.");
        codes.put("81", "PIN cryptographic error found.");
        codes.put("82", "Negative CAM, dCVV, iCVV, or CVV results");
        codes.put("83", "Unable to verify PIN");
        codes.put(
                "85",
                "No reason to decline a request for account number verification, address verification, CVV2 verification, or a credit voucher or merchandise return");
        codes.put("91", "Issuer unavailable or switch inoperative .");
        codes.put("92", "Destination cannot be found for routing");
        codes.put("93", "Transaction cannot be completed, violation of law");
        codes.put("94", "Duplicate Transmission");
        codes.put("95", "Reconcile error");
        codes.put("96", "System malfunction");
        codes.put("B1", "Surcharge amount not permitted on Visa cards ");
        codes.put("N0", "Force STIP");
        codes.put("N3", "Cash service not available");
        codes.put("N4", "Cashback request exceeds issuer limit");
        codes.put("N7", "Decline for CVV2 failure");
        codes.put("P2", "Invalid biller information");
        codes.put("P5", "PIN Change/Unblock request declined");
        codes.put("P6", "Unsafe PIN");
        codes.put("Q1", "Card Authentication failed");
        codes.put("R0", "Stop Payment Order");
        codes.put("R1", "Revocation of Authorization Order");
        codes.put("R3", "Revocation of All Authorizations Order");
        codes.put("XA", "Forward to issuer");
        codes.put("XD", "Forward to issuer");
        codes.put("Z3", "Unable to go online");
    }
}
