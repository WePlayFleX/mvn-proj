package org.example.utils;

public class MailClient {
    public static void main(String[] args) {
        MailUtils.send(AppConstants.EMAIL_ACC,"This is spam!", "This is spam!");
    }
}
