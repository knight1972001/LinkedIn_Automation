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

    public static List<String> getAllLinkedInLink(){
        List<String> links = new ArrayList<String>();

        String path_file = "data/linkedIn-link.txt";

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

    public static List<String> getAllIndeedLink(){
        List<String> links = new ArrayList<String>();

        String path_file = "data/indeed-link.txt";

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
        // Define a pattern to match numbers with optional commas
        Pattern pattern = Pattern.compile("\\d{1,3}(,\\d{3})*");

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(input);

        // Find the first match (assuming there is at least one number)
        if (matcher.find()) {
            // Remove commas and parse the matched number
            String matchedNumber = matcher.group().replaceAll(",", "");
            return Integer.parseInt(matchedNumber);
        } else {
            // Return a default value or throw an exception if no number is found
            return -1;  // Default value (-1) or throw an exception
        }
    }

    public static String subtractStrings(String text1, String text2) {
        // Check if text2 is a suffix of text1
        if (text1.endsWith(text2)) {
            // Remove text2 from the end of text1
            return text1.substring(0, text1.length() - text2.length()).trim();
        } else {
            // No subtraction possible, return the original text1
            return text1;
        }
    }

    public static String extractJobIdLinkedIn(String url){
        // Regular expression pattern to match the job ID
        String pattern = "currentJobId=(\\d+)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Create Matcher object
        Matcher m = r.matcher(url);

        // Find the job ID
        if (m.find()) {
            String jobId = m.group(1);
            return jobId;
        } else {
            return "";
        }
    }

    public static String remakeUrlLinkedIn(String url){
        String jobId = extractJobIdLinkedIn(url);

        try{
            if(!jobId.equals("")){
                String newUrl = "https://www.linkedin.com/jobs/collections/recommended/?currentJobId="+jobId;
                return newUrl;
            }else{
                throw new Exception("Job ID not found.");
            }
        }catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return jobId;
    }

    public static String extractJobIdIndeed(String url){
        // Regular expression pattern to match the job ID
        String pattern = "vjk=([a-zA-Z0-9]+)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Create Matcher object
        Matcher m = r.matcher(url);

        // Find the job ID
        if (m.find()) {
            String jobId = m.group(1);
            return jobId;
        } else {
            return "";
        }
    }

    public static String remakeUrlIndeed(String url){
        String jobId = extractJobIdIndeed(url);

        try{
            if(!jobId.equals("")){
                String newUrl = "https://ca.indeed.com/jobs?q=QA+Automation+Engineer&vjk="+jobId;
                return newUrl;
            }else{
                throw new Exception("Job ID not found.");
            }
        }catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return jobId;
    }
}
