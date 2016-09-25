package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.core.CoreBankingProvider;
import com.ocularminds.oswitch.core.Fault;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOSource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.ocularminds.oswitch.common.Identifier;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CoreTest {

    @Mock
    ISOSource source;

    @Mock
    ISOMsg request;

    @Mock
    CoreBankingProvider provider;

    @Mock
    OsService service;

    @Mock
    Map<String, String> processors;

    @InjectMocks
    private OsCore core;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testProcessFailedBalanceInquiry() throws Exception {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(3, "201234");
        isoMsg.set(4, "10000");
        isoMsg.set(7, "110722180");
        isoMsg.set(11, "123456");
        isoMsg.set(44, "A5DFGR");
        isoMsg.set(105, "ABCDEFGHIJ 1234567890");
        when(request.clone()).thenReturn(isoMsg);
        when(processors.get(anyString())).thenReturn("BALANCE");
        when(request.getBytes(52)).thenReturn("BALANCE".getBytes());
        when(provider.balance(anyObject())).thenReturn(new Fault("30", "Insufficient balance"));
        boolean response = core.process(source, request);
        assertTrue(response);
    }

    @Test
    public void testProcessSuccessBalanceInquiry() throws Exception {
        Fault fault = new Fault("00", "Success Ok.");
        final String LEDGER_BALANCE = "ledger";
        final String AVAILABLE_BALANCE = "available";
        Map<String, Double> balances = new HashMap();
        balances.put(AVAILABLE_BALANCE, new Double(150.00));
        balances.put(LEDGER_BALANCE, new Double(150.00));
        fault.setData(balances);
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(3, "201234");
        isoMsg.set(4, "10000");
        isoMsg.set(7, "110722180");
        isoMsg.set(11, "123456");
        isoMsg.set(44, "A5DFGR");
        isoMsg.set(105, "ABCDEFGHIJ 1234567890");
        when(request.clone()).thenReturn(isoMsg);
        when(processors.get(anyString())).thenReturn("BALANCE");
        when(request.getBytes(52)).thenReturn("BALANCE".getBytes());
        when(provider.balance(anyObject())).thenReturn(fault);
        boolean response = core.process(source, request);
        assertTrue(response);
    }

    @Test
    public void testProcessFundTransfer() throws Exception {

        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(3, "201234");
        isoMsg.set(4, "10000");
        isoMsg.set(7, "110722180");
        isoMsg.set(11, "123456");
        isoMsg.set(44, "A5DFGR");

        isoMsg.set(102, "419");
        isoMsg.set(103, "914");
        isoMsg.set(105, "ABCDEFGHIJ 1234567890");

        when(request.clone()).thenReturn(isoMsg);
        when(processors.get(anyString())).thenReturn("TRANSFER");
        when(request.getBytes(52)).thenReturn("TRANSFER".getBytes());
        when(provider.transfer(anyObject())).thenReturn(new Fault("00", "Success"));
        // Mockito.doNothing().when(transactions.create(anyObject()));
        boolean response = core.process(source, request);
        assertTrue(response);
    }

    @Test
    public void testProcessBillMessage() throws Exception {

        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0200");
        isoMsg.set(3, "201234");
        isoMsg.set(4, "10000");
        isoMsg.set(7, "110722180");
        isoMsg.set(11, "123456");
        isoMsg.set(44, "A5DFGR");
        isoMsg.set(105, "ABCDEFGHIJ 1234567890");

        when(request.clone()).thenReturn(isoMsg);
        when(processors.get(anyString())).thenReturn("BILL");
        when(request.getBytes(52)).thenReturn("BILL".getBytes());
        when(provider.balance(anyObject())).thenReturn(new Fault("30", "Insufficient balance"));
        boolean response = core.process(source, request);
        assertTrue(response);
    }
}
