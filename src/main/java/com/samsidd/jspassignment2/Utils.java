package com.samsidd.jspassignment2;

import com.samsidd.jspassignment2.models.Book;
import com.samsidd.jspassignment2.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static String hashString(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String text = "Text to hash, cryptographically.";

        // Change this to UTF-16 if needed
        md.update(text.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();

        return String.format("%064x", new BigInteger(1, digest));
    }

    public static void initializeNewUserSessionVariables(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("userLoggedIn", true);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userDetails", user);
        session.setAttribute("reservedBooks", new ArrayList<Book>());
        session.setAttribute("filterBooksByTopicID", "0");
    }

    public static void updateUserSessionVariables(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("userLoggedIn", true);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userDetails", user);

    }

    public static void initializeUserSessionVariables(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("userLoggedIn", true);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userDetails", user);
        session.setAttribute("reservedBooks", null);
        session.setAttribute("filterBooksByTopicID", "0");
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        Pattern rfc2822 = Pattern.compile(
                "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
        );

        if (!rfc2822.matcher(email).matches()) {
            result = false;
        }

        return result;
    }

    public static boolean validateUserData(User user) {
        boolean isValid = true;

        if(user.getFname().length() < 2) isValid = false;
        if(user.getLname().length() < 2) isValid = false;
        if(user.getUsername().length() < 2) isValid = false;
        if(user.getPassword().length() < 5) isValid = false;

        return isValid;
    }
}
