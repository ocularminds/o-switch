package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.core.Entry;
import com.ocularminds.oswitch.core.Fault;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.ocularminds.oswitch.common.Identifier;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProviderTest {

    // @Mock
    // private Repository repository;

    @Test
    public void testIdentifierNumber() throws Exception {
        String actual = new Identifier().next();
        final int EXPECTED = 10;
        assertEquals(new Integer(actual.length()), new Integer(EXPECTED));
    }

    @Test
    public void testCompareTwoEqualEntries() {
        Entry e = new Entry("10", "21", 55.25);
        Entry e2 = new Entry("10", "21", 55.25);
        assertEquals(e, e2);

    }

    @Test
    public void testCompareTwoUnequalEntries() {
        Entry e = new Entry("10", "21", 55.25);
        Entry e2 = new Entry("11", "21", 55.25);
        assertFalse(e.equals(e2));
    }

    @Test
    public void testFaultErrorAndMessage() {
        final String EXPECTED_MESSAGE = "Invalid account";
        Fault f = new Fault("10", "Invalid account");
        assertTrue(f.getError().equals("10"));
        assertTrue(f.getFault().equals(EXPECTED_MESSAGE));
    }

    @Test
    public void testFaultNullDataObject() {
        final String TEST_MESSAGE = "Invalid account";
        Fault f = new Fault("10", TEST_MESSAGE);
        boolean isEmpty = f.getData() == null;
        assertTrue(isEmpty);
    }

    @Test
    public void testFaultTypeOfDataObject() {
        final String TEST_MESSAGE = "Invalid account";
        int[] nums = {1, 4, 7, 1, 9};
        List<Integer> list = Arrays.asList(Arrays.stream(nums).boxed().toArray(Integer[]::new));
        Fault f = new Fault("10", TEST_MESSAGE);
        f.setData(list);
        boolean typeofclass = f.getData() instanceof List;
        assertTrue(typeofclass);
    }
}
