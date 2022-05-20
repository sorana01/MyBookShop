package com.example.book_shop.exceptions;

public class WrongPasswordConfirmationException extends Exception{
    public WrongPasswordConfirmationException() {
        super("Passwords do not match!");
    }
}
