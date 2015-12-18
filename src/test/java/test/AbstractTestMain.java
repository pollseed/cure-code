package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import org.apache.commons.lang.StringUtils;

public class AbstractTestMain {
    protected void eq(final String expected, final String actual) {
        t$0(expected, actual);
        assertEquals(expected, actual);
    }

    protected void eq(final int expected, final int actual) {
        assertEquals(expected, actual);
    }

    protected void ng(final String expected, final String actual) {
        t$0(expected, actual);
        assertNotSame(expected, actual);
    }

    protected void ng(final String expected, final boolean actual) {
        t$0(expected);
        assertFalse(expected, actual);
    }

    private void t$0(final String expected, final String actual) {
        a$0(StringUtils.isBlank(expected) || StringUtils.isBlank(actual));
    }

    private void t$0(final String expected) {
        a$0(StringUtils.isBlank(expected));
    }

    private void a$0(boolean a) {
        if (a)
            throw new AssertionError("args bug error");
    }
}
