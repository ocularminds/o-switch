package com.ocularminds.oswitch.core;

import com.ocularminds.oswitch.common.Identifier;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CoreBankingProviderTest {

    @Test
    public void testBalance() throws Exception {
        String actual = new Identifier().next();
        final int EXPECTED = 10;
        assertEquals(new Integer(actual.length()), new Integer(EXPECTED));
    }

    // Fault balance(Entry entry);

    // Fault transfer(Entry entry);

    // Fault bill(Entry entry);

}
