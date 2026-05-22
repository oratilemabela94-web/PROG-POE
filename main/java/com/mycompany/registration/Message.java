/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author Student
 */
import java.util.Random;
import java.util.regex.Pattern;

public class Message {

    private static int totalMessages = 0;

    // Check recipient number
    public boolean checkRecipientCell(String number) {

        String regex = "^\\+27\\d{9}$";

        return Pattern.matches(regex, number);
    }

    // Check message length
    public String checkMessageLength(String message) {

        if (message.length() <= 250) {

            return "Message ready to send.";
        } else {

            int exceed = message.length() - 250;

            return "Message exceeds 250 characters by "
                    + exceed
                    + ", please reduce the size.";
        }
    }

    // Generate Message ID
    public String generateMessageID() {

        Random random = new Random();

        long id = 1000000000L + random.nextInt(900000000);

        return String.valueOf(id);
    }

    // Create Message Hash
    public String createMessageHash(int messageNumber,
                                    String message,
                                    String messageID) {

        String[] words = message.split(" ");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        String firstTwo =
                messageID.substring(0, 2);

        return firstTwo + ":" + messageNumber
                + ":" + firstWord + lastWord;
    }

    // Sent message option
    public String sentMessage(int option) {

        if (option == 1) {

            totalMessages++;

            return "Message successfully sent.";
        }

        else if (option == 2) {

            return "Press 0 to delete the message.";
        }

        else if (option == 3) {

            return "Message successfully stored.";
        }

        else {

            return "Invalid option.";
        }
    }

    // Return total messages sent
    public int returnTotalMessages() {

        return totalMessages;
    }
}
