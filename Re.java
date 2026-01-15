import java.util.ArrayList;
public class Re {
    public static void main(String[] args) {
        String phoneNumbers = "^(\\(\\d{3}\\)\\s?|\\d{3}[-.]?)\\d{3}[-.]?\\d{4}$";
        String text = "I like to eat ice cream at the Garden State Plaza.";
        String regex = "\\b[A-Z][a-z]*\\b";
        String[] testNumbers = {
            "(123) 456-7890",
            "123-456-7890",
            "123.456.7890",
            "1234567890",
            "12-3456-7890"
        };
        for(String number : testNumbers) {
            if(number.matches(phoneNumbers)) {
                System.out.println(number + " is a valid phone number.");
            } else {
                System.out.println(number + " is NOT a valid phone number.");
            }
        }
        for(String word : text.split(" ")) {
            if(word.matches(regex)) {
                System.out.println(word + " starts with a capital letter.");
            }
        }
    }
}