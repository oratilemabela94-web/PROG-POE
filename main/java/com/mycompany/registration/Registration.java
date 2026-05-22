/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registration;

/**
 *
 * @author Student
 */
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Registration {

    static String storedUsername;
    static String storedPassword;
    static int totalMessages = 0;

    // ================= USERNAME VALIDATION =================
    public static boolean checkUserName(String username) {

        return username.contains("_") && username.length() <= 5;
    }

    // ================= PASSWORD VALIDATION =================
    public static boolean checkPasswordComplexity(String password) {

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            }

            if (Character.isDigit(ch)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // ================= CELL NUMBER VALIDATION =================
    public static boolean checkCellPhoneNumber(String number) {

        String regex = "^\\+27\\d{9}$";

        return Pattern.matches(regex, number);
    }

    // ================= REGISTER USER =================
    public static String registerUser(String username,
                                      String password,
                                      String cellPhoneNumber) {

        if (!checkUserName(username)) {

            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {

            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhoneNumber)) {

            return "Cell phone number incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        storedUsername = username;
        storedPassword = password;

        return "User has been registered successfully.";
    }

    // ================= LOGIN USER =================
    public static boolean loginUser(String username,
                                    String password) {

        return username.equals(storedUsername)
                && password.equals(storedPassword);
    }

    // ================= LOGIN STATUS =================
    public static String returnLoginStatus(boolean loginStatus,
                                           String firstName,
                                           String lastName) {

        if (loginStatus) {

            return "Welcome "
                    + firstName + " "
                    + lastName
                    + ", it is great to see you again.";
        }

        else {

            return "Username or password incorrect, please try again.";
        }
    }

    // ================= CHECK MESSAGE LENGTH =================
    public static String checkMessageLength(String message) {

        if (message.length() <= 250) {

            return "Message ready to send.";
        }

        else {

            int extra = message.length() - 250;

            return "Message exceeds 250 characters by "
                    + extra
                    + ", please reduce the size.";
        }
    }

    // ================= GENERATE MESSAGE ID =================
    public static String generateMessageID() {

        Random random = new Random();

        long number =
                1000000000L + random.nextInt(900000000);

        return String.valueOf(number);
    }

    // ================= CREATE MESSAGE HASH =================
    public static String createMessageHash(int messageNumber,
                                           String message,
                                           String messageID) {

        String[] words = message.split(" ");

        String firstWord =
                words[0].toUpperCase();

        String lastWord =
                words[words.length - 1].toUpperCase();

        String firstTwo =
                messageID.substring(0, 2);

        return firstTwo
                + ":"
                + messageNumber
                + ":"
                + firstWord + lastWord;
    }

    // ================= MESSAGE ACTION =================
    public static String sentMessage(int option) {

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

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ================= REGISTRATION =================
        System.out.println("===== USER REGISTRATION =====");

        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.print("Enter SA Cell Number: ");
        String cellNumber = input.nextLine();

        String registerResult =
                registerUser(username,
                             password,
                             cellNumber);

        System.out.println(registerResult);

        // ================= LOGIN =================
        if (registerResult.equals(
                "User has been registered successfully.")) {

            System.out.println("\n===== LOGIN =====");

            System.out.print("Enter Username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = input.nextLine();

            boolean loginStatus =
                    loginUser(loginUsername,
                              loginPassword);

            String loginMessage =
                    returnLoginStatus(loginStatus,
                                      firstName,
                                      lastName);

            System.out.println(loginMessage);

            // ================= QUICKCHAT =================
            if (loginStatus) {

                boolean running = true;

                while (running) {

                    System.out.println("\n===== QUICKCHAT =====");

                    System.out.println("1. Send Message");
                    System.out.println("2. Disregard Message");
                    System.out.println("3. Store Message");
                    System.out.println("4. Exit");

                    System.out.print("Choose an option: ");
                    int option = input.nextInt();
                    input.nextLine();

                    // ===== EXIT =====
                    if (option == 4) {

                        System.out.println(
                                "Application terminated.");

                        running = false;
                        break;
                    }

                    // ===== RECIPIENT =====
                    System.out.print(
                            "Enter recipient number: ");

                    String recipient =
                            input.nextLine();

                    if (checkCellPhoneNumber(recipient)) {

                        System.out.println(
                                "Cell phone number successfully captured.");
                    }

                    else {

                        System.out.println(
                                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");

                        continue;
                    }

                    // ===== MESSAGE =====
                    System.out.print("Enter message: ");

                    String message =
                            input.nextLine();

                    String lengthResult =
                            checkMessageLength(message);

                    System.out.println(lengthResult);

                    // Stop if message too long
                    if (!lengthResult.equals(
                            "Message ready to send.")) {

                        continue;
                    }

                    // ===== MESSAGE ID =====
                    String messageID =
                            generateMessageID();

                    System.out.println(
                            "Message ID generated: "
                                    + messageID);

                    // ===== MESSAGE HASH =====
                    String hash =
                            createMessageHash(
                                    totalMessages,
                                    message,
                                    messageID);

                    System.out.println(
                            "Message Hash: "
                                    + hash);

                    // ===== MESSAGE RESULT =====
                    String result =
                            sentMessage(option);

                    System.out.println(result);

                    // ===== TOTAL MESSAGES =====
                    System.out.println(
                            "Total Messages Sent: "
                                    + totalMessages);
                }
            }
        }

        input.close();
    }
}