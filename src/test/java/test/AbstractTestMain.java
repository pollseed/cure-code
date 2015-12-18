package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

public class AbstractTestMain {

    protected void eq(final String expected, final String actual) {
        assertEquals(expected, actual);
    }

    protected void eq(final int expected, final int actual) {
        assertEquals(expected, actual);
    }

    protected void ng(final String expected, final String actual) {
        assertNotSame(expected, actual);
    }

    protected void ng(final String expected, final boolean actual) {
        assertFalse(expected, actual);
    }
}
