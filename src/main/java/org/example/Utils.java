package org.example;

import java.util.List;

public class Utils {
    public static boolean isValidString(String text, List<String> relatePosition) {
        for (String position : relatePosition) {
            if (text.contains(position)) {
                return true;
            }
        }
        return false;
    }
}
