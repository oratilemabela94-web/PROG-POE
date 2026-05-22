/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import com.mycompany.registration.Registration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */

public class Testing {

    // ================= USERNAME TESTS =================

    @Test
    public void testUserNameCorrectlyFormatted() {

        boolean result =
                Registration.checkUserName("kyl_1");

        assertTrue(result);
    }

    @Test
    public void testUserNameIncorrectlyFormatted() {

        boolean result =
                Registration.checkUserName("kyle!!!!!!");

        assertFalse(result);
    }

    // ================= PASSWORD TESTS =================

    @Test
    public void testPasswordMeetsComplexity() {

        boolean result =
                Registration.checkPasswordComplexity(
                        "Ch&&sec@ke99!"
                );

        assertTrue(result);
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {

        boolean result =
                Registration.checkPasswordComplexity(
                        "password"
                );

        assertFalse(result);
    }

    // ================= CELL NUMBER TESTS =================

    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {

        boolean result =
                Registration.checkCellPhoneNumber(
                        "+27838968976"
                );

        assertTrue(result);
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {

        boolean result =
                Registration.checkCellPhoneNumber(
                        "08966553"
                );

        assertFalse(result);
    }

    // ================= REGISTER USER TESTS =================

    @Test
    public void testRegisterUserSuccess() {

        String result =
                Registration.registerUser(
                        "kyl_1",
                        "Ch&&sec@ke99!",
                        "+27838968976"
                );

        assertEquals(
                "User has been registered successfully.",
                result
        );
    }

    @Test
    public void testRegisterUserUsernameFail() {

        String result =
                Registration.registerUser(
                        "kyle!!!!!!",
                        "Ch&&sec@ke99!",
                        "+27838968976"
                );

        assertEquals(
                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                result
        );
    }

    @Test
    public void testRegisterUserPasswordFail() {

        String result =
                Registration.registerUser(
                        "kyl_1",
                        "password",
                        "+27838968976"
                );

        assertEquals(
                "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                result
        );
    }

    @Test
    public void testRegisterUserCellFail() {

        String result =
                Registration.registerUser(
                        "kyl_1",
                        "Ch&&sec@ke99!",
                        "08966553"
                );

        assertEquals(
                "Cell phone number incorrectly formatted or does not contain an international code; please correct the number and try again.",
                result
        );
    }

    // ================= LOGIN TESTS =================

    @Test
    public void testLoginSuccessful() {

        Registration.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        boolean result =
                Registration.loginUser(
                        "kyl_1",
                        "Ch&&sec@ke99!"
                );

        assertTrue(result);
    }

    @Test
    public void testLoginFailed() {

        Registration.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        boolean result =
                Registration.loginUser(
                        "wrong",
                        "wrong123"
                );

        assertFalse(result);
    }

    // ================= LOGIN STATUS TESTS =================

    @Test
    public void testReturnLoginStatusSuccess() {

        String result =
                Registration.returnLoginStatus(
                        true,
                        "Kyle",
                        "Smith"
                );

        assertEquals(
                "Welcome Kyle Smith, it is great to see you again.",
                result
        );
    }

    @Test
    public void testReturnLoginStatusFailure() {

        String result =
                Registration.returnLoginStatus(
                        false,
                        "Kyle",
                        "Smith"
                );

        assertEquals(
                "Username or password incorrect, please try again.",
                result
        );
    }

    // ================= MESSAGE LENGTH TESTS =================

    @Test
    public void testMessageLengthSuccess() {

        String result =
                Registration.checkMessageLength(
                        "Hi Mike, can you join us for dinner tonight?"
                );

        assertEquals(
                "Message ready to send.",
                result
        );
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage =
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        String result =
                Registration.checkMessageLength(
                        longMessage
                );

        assertEquals(
                "Message exceeds 250 characters by 3, please reduce the size.",
                result
        );
    }

    // ================= MESSAGE ID TEST =================

    @Test
    public void testGenerateMessageID() {

        String id =
                Registration.generateMessageID();

        assertNotNull(id);

        assertTrue(id.length() >= 9);
    }

    // ================= MESSAGE HASH TEST =================

    @Test
    public void testCreateMessageHash() {

        String hash =
                Registration.createMessageHash(
                        0,
                        "Hi Mike, can you join us for dinner tonight?",
                        "0012345678"
                );

        assertEquals(
                "00:0:HITONIGHT?",
                hash
        );
    }

    // ================= SEND MESSAGE TESTS =================

    @Test
    public void testSendMessage() {

        String result =
                Registration.sentMessage(1);

        assertEquals(
                "Message successfully sent.",
                result
        );
    }

    @Test
    public void testDisregardMessage() {

        String result =
                Registration.sentMessage(2);

        assertEquals(
                "Press 0 to delete the message.",
                result
        );
    }

    @Test
    public void testStoreMessage() {

        String result =
                Registration.sentMessage(3);

        assertEquals(
                "Message successfully stored.",
                result
        );
    }

    @Test
    public void testInvalidOption() {

        String result =
                Registration.sentMessage(10);

        assertEquals(
                "Invalid option.",
                result
        );
    }
}