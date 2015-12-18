package test;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

public class TestMain extends AbstractTestMain {
    @Test
    public void OK_test_replace() {
        final String expected = "\\_hoge\\_fuga\\_piyo\\_";
        final String actual = "_hoge_fuga_piyo_";
        eq(expected, actual.replace("_", "\\_"));
    }

    @Test
    public void NG_test_replace() {
        final String expected = "\\_hoge_fuga_piyo_";
        final String actual = "_hoge_fuga_piyo_";
        ng(expected, actual.replace("_", "\\_"));
    }

    @Test
    public void OK_test_split() {
        final List<String> expected =
                Lists.newArrayList("hoge", "fuga", "p", "iyo", "t", "tt", "uuu");
        final String actual = " hoge　fuga p  iyo　　t     　　　　　    　　  tt uuu 　";
        int i = 0;
        for (final String str : StringUtils.split(actual, " |　")) {
            eq(expected.get(i), str);
            i++;
        }
    }

    @Test
    public void NG_test_integer_contains_string() {
        final List<String> expected = Lists.newArrayList("2", "3", "5", "1", "4");
        final List<Integer> actual = Lists.newArrayList(1, 2, 3, 4);
        for (final String str : expected) {
            ng("assertion error  contain", actual.contains(str));
        }
    }

    @Test
    public void OK_test_parse_integer() {
        final int expected = 1;
        final String actual = "0000001";
        eq(expected, Integer.parseInt(actual));
    }

    @Test(expected = NumberFormatException.class)
    public void NG_test_parse_integer1() {
        final String actual = "      1      ";
        Integer.parseInt(actual);
    }

    @Test(expected = NumberFormatException.class)
    public void NG_test_parse_integer2() {
        final String actual = "1      ";
        Integer.parseInt(actual);
    }

    @Test(expected = NumberFormatException.class)
    public void NG_test_parse_integer3() {
        final String actual = "　　　　1　　　　";
        Integer.parseInt(actual);
    }
}
