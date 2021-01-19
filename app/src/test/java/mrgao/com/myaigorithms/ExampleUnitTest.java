package mrgao.com.myaigorithms;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

//        assertEquals(3, StringType.countSubstrings("abc"));

    }

    public int countSubstrings(String s) {
        int n = "abc".length();
        int sum = 0;
        boolean[][] sub = new boolean[n][n];
        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                if (l == 0) {
                    sub[i][j] = true;
                } else if (l == 1) {
                    sub[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    sub[i][j] = s.charAt(i) == s.charAt(j) && sub[i + 1][j - 1];
                }
                if (sub[i][j]) {
                    sum++;
                    System.out.println("sub[i][j]"+i+j);
                }
            }
        }
        return sum;
    }



}