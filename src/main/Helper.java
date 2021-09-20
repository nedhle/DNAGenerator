package main;

import java.util.List;
import java.util.Random;

public class Helper {

    public static int generateNumber(int bound) {
        Random rnd = new Random();
        return rnd.nextInt(bound);
    }

    public static int computeDiff(String s1, String s2) {
        int count = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public static boolean checkList(List<String> list, String s) {
        for (String s1 : list) {
            if (computeDiff(s1, s) < 3) return false;
        }
        return true;
    }
}
