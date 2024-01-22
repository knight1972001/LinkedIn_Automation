package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean isValidString(String text, List<String> relatePosition) {
        for (String position : relatePosition) {
            if (text.contains(position)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getAllTheAppliedLink(){
        List<String> links = new ArrayList<String>();

        String path_file = "data/applied-links.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Add each line to the linked list
                links.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return links;
    }

    public static int extractNumber(String input) {
        // Define a pattern to match numbers
        Pattern pattern = Pattern.compile("\\d+");

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(input);

        // Find the first match (assuming there is at least one number)
        if (matcher.find()) {
            // Parse and return the matched number
            return Integer.parseInt(matcher.group());
        } else {
            // Return a default value or throw an exception if no number is found
            return -1;  // Default value (-1) or throw an exception
        }
    }
}
