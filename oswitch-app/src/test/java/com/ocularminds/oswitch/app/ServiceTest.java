package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.core.CoreBankingProvider;
import com.ocularminds.oswitch.core.Entry;
import com.ocularminds.oswitch.core.Fault;
import com.ocularminds.oswitch.app.model.Cardlex;
import com.ocularminds.oswitch.app.model.Cardless;
import com.ocularminds.oswitch.app.model.Preference;
import com.ocularminds.oswitch.app.model.Preferences;
import com.ocularminds.oswitch.app.model.Transaction;
import com.ocularminds.oswitch.app.model.Transactions;
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
public class ServiceTest {

    @Mock
    Transactions transactions;

    @Mock
    Cardless cardless;

    @Mock
    Preferences preferences;

    @InjectMocks
    OsService service;

    private Entry entry = new Entry("419", "914", 5000.00);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAddTransaction() throws Exception {
        entry.setType(Entry.Type.DEBIT);
        Transaction tran = new Transaction(entry);
        tran.setReference("11110200");
        tran.setBeneficiary("Colosin Amen");
        tran.setChannel(Transaction.Channel.ATM);
        Mockito.doNothing().when(transactions).add(tran);
        service.create(tran);
        // assertTrue(response);
    }

    @Test
    public void testFindTransaction() throws Exception {
        entry.setType(Entry.Type.DEBIT);
        Transaction tran = new Transaction(entry);
        tran.setReference("11110200");
        tran.setBeneficiary("Colosin Amen");
        tran.setChannel(Transaction.Channel.ATM);

        when(service.find(tran)).thenReturn(tran);
        Transaction t = service.find(new Transaction());
        assertEquals(tran, t);
        assertEquals("419", t.getDebit());
    }

    @Test
    public void testAddCardlex() throws Exception {
        Cardlex c = new Cardlex();
        c.setAmount(2.00);
        c.setAccount("419");
        c.setToken("1234");
        c.setDate(new java.util.Date());
        when(cardless.add(anyObject())).thenReturn("0123456789");
        service.create(c);
        // assertTrue(response);
    }

    @Test
    public void testFindCardlex() throws Exception {
        Cardlex c = new Cardlex();
        c.setAmount(2.00);
        c.setAccount("419");
        c.setToken("1234");
        c.setDate(new java.util.Date());

        when(service.find(anyString(), anyDouble())).thenReturn(c);
        Cardlex e = service.find("419", 1.00);
        assertEquals(c, e);
        assertEquals("1234", e.getToken());
    }

    @Test
    public void testAddPreference() throws Exception {
        Preference c = new Preference();
        c.setAmount(2.00);
        c.setAccount("419");
        c.setToken("1234");
        c.setLanguage("Yoruba");
        Mockito.doNothing().when(preferences).add(c);
        service.create(c);
        // assertTrue(response);
    }

    @Test
    public void testFindPreference() throws Exception {
        Preference c = new Preference();
        c.setAmount(2.00);
        c.setAccount("419");
        c.setToken("1234");
        c.setLanguage("Yoruba");

        when(service.find(anyString())).thenReturn(c);
        Preference e = service.find("419");
        assertEquals("Yoruba", e.getLanguage());
    }
}
