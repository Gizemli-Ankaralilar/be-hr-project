package com.team1.utility;
import java.util.Random;
import java.util.UUID;

public class GeneratePassword {

    public static String generatePassword() {
        String character = "QWERTYUIOPĞÜASDFGHJKLŞİZXCVBNMÖÇ"+
                "qwertyuıopğüasdfghjklşizxcvbnmöç"+
                "1234567890";

        String password = "";

        for (int i=0; i < 8; i++)
        {

            Random rastgelesifre = new Random();
            password += character.charAt(rastgelesifre.nextInt(character.length()));
        }
        return password;
    }
}
