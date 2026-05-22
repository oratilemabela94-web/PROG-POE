/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author Student
 */
import java.util.regex.Pattern;

public class Login {

    private String storedUsername;
    private String storedPassword;

    // Username validation
    public boolean checkUserName(String username) {

        return username.contains("_") && username.length() <= 5;
    }

    // Password validation
    public boolean checkPasswordComplexity(String password) {

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

    // Cell phone validation
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {

        String regex = "^\\+27\\d{9}$";

        return Pattern.matches(regex, cellPhoneNumber);
    }

    // Register user
    public String registerUser(String username,
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

    // Login method
    public boolean loginUser(String username, String password) {

        return username.equals(storedUsername)
                && password.equals(storedPassword);
    }

    // Login status message
    public String returnLoginStatus(boolean loginStatus,
                                    String firstName,
                                    String lastName) {

        if (loginStatus) {

            return "Welcome " + firstName + ", "
                    + lastName + " it is great to see you again.";
        } else {

            return "Username or password incorrect, please try again.";
        }
    }
}
