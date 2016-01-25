package test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
    private static enum $C {
        REPLACE('\u005f'), SENTINEL('\uff20');
        char word;

        private $C(final char w) {
            this.word = w;
        }
    }

    @Test
    public void t1() {
        final String camelCase = snakeCaseToCamelCase("user_id, member_id");
        assertThat(camelCase, is("userId, memberId"));
        final String snakeCase = camelCaseToSnakeCase(camelCase);
        assertThat(snakeCase, is("user_id, member_id"));
    }

    /**
     * スネークケースをキャメルケースに変換します.<br>
     * ※スネークケース以外の文字列には非対応です.
     * 
     * @param snakeCase
     *            スネークケースの文字列
     * @return キャメルケース
     */
    public static String snakeCaseToCamelCase(final String snakeCase) {
        invalidIfError(snakeCase);
        final StringBuilder sb = new StringBuilder();
        final char[] cs = (snakeCase + $C.SENTINEL.word).toCharArray();
        boolean convertedToUpperCase = false;
        for (int i = 0; i < cs.length; i++) {
            if ($C.SENTINEL.word == cs[i + 1]) {
                sb.append(cs[i]);
                break;
            }
            if (convertedToUpperCase) {
                // _ をスキップさせる
                convertedToUpperCase = false;
            } else if ($C.REPLACE.word == cs[i]) {
                convertedToUpperCase = true;
                sb.append(String.valueOf(cs[i + 1]).toUpperCase());
            } else {
                sb.append(cs[i]);
            }
        }
        return sb.toString();
    }

    private static void invalidIfError(final String str) {
        if (isBlank(str)) {
            new IllegalArgumentException("argument is blank value.");
        }
    }

    /**
     * キャメルケースをスネークケースに変換します.<br>
     * ※キャメルケース以外の文字列には非対応です.
     * 
     * @param camelCase
     *            キャメルケースの文字列
     * @return スネークケース
     */
    public static String camelCaseToSnakeCase(final String camelCase) {
        invalidIfError(camelCase);
        final StringBuilder sb = new StringBuilder();
        for (final char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append($C.REPLACE.word).append(String.valueOf(c).toLowerCase());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
